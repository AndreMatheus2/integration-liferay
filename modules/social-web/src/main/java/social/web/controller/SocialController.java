package social.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.liferay.portletmvc4spring.bind.annotation.ActionMapping;
import com.liferay.portletmvc4spring.bind.annotation.RenderMapping;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Locale;

import javax.portlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import social.web.dto.PostRequest;
import social.web.dto.PostResponse;

/**
 * @author andre-arao
 */
@Controller
@RequestMapping("VIEW")
public class SocialController {


	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static HttpGet requestGet = new HttpGet();
	private static String url = "http://localhost:8080/o/integration/users/";


	@ModelAttribute("postRequest")
	public PostRequest getPostRequestModelAttribute() {

		return new PostRequest();
	}

	@RenderMapping
	public String prepareView() {

		return "form";
	}

	@RenderMapping(params = "javax.portlet.action=success")
	public String showPosts(@ModelAttribute("postRequest") PostRequest postRequest,
							   ModelMap modelMap, RenderResponse renderResponse) throws IOException {

		String responseAlert = null;

		try {
			requestGet.setURI(new URI(url + postRequest.getUserId() + "/posts"));
			requestGet.setHeader("followerId", String.valueOf(postRequest.getFollowerId()));
			HttpEntity httpEntity = httpClient.execute(requestGet).getEntity();
			String response = EntityUtils.toString(httpEntity);
			responseAlert = response;

			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<List<PostResponse>>() {} .getType();
			List<PostResponse> posts =  gson.fromJson(response, type);

			modelMap.addAttribute("posts", posts);

			return "listPosts";

		} catch (IllegalStateException | JsonSyntaxException e){
			modelMap.addAttribute("error", "Voce nao pode ver os posts desse usuario, porque nao segue ele!");
			e.printStackTrace();
		}
		catch (URISyntaxException | ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "form";
	}

	@ActionMapping(params = "acao=voltarForm")
	public void redirecionarParaPaginaDeDestino(ActionRequest actionRequest, ActionResponse actionResponse) {
		// Redirecionar para a página de destino
		actionResponse.setRenderParameter("javax.portlet.action", "voltarForm");
	}
	@RenderMapping(params = "javax.portlet.action=voltarForm")
	public String renderizarPaginaDeDestino(RenderRequest renderRequest, RenderResponse renderResponse) {
		// Renderizar para a página de destino
		return "form";
	}

	@ActionMapping
	public void submitApplicant(
			@ModelAttribute("postRequest") PostRequest postRequest, BindingResult bindingResult,
			ModelMap modelMap, Locale locale, ActionResponse actionResponse,
			SessionStatus sessionStatus) {

		_localValidatorFactoryBean.validate(postRequest, bindingResult);

		if (!bindingResult.hasErrors()) {
			if (_logger.isDebugEnabled()) {
				_logger.debug("followerId=" + postRequest.getFollowerId());
				_logger.debug("userId=" + postRequest.getUserId());
			}

			MutableRenderParameters mutableRenderParameters =
				actionResponse.getRenderParameters();

			mutableRenderParameters.setValue("javax.portlet.action", "success");

			sessionStatus.setComplete();
		}
		else {
			bindingResult.addError(
				new ObjectError(
					"postList",
					_messageSource.getMessage(
						"please-correct-the-following-errors", null, locale)));
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		SocialController.class);

	@Autowired
	private LocalValidatorFactoryBean _localValidatorFactoryBean;

	@Autowired
	private MessageSource _messageSource;

}
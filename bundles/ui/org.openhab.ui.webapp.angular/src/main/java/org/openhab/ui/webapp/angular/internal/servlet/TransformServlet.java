package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openhab.core.transform.TransformationException;
import org.openhab.core.transform.TransformationHelper;
import org.openhab.core.transform.TransformationService;
import org.openhab.ui.webapp.angular.internal.WebAppActivator;

public class TransformServlet extends BaseServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		if (req instanceof HttpServletRequest) {
			get((HttpServletRequest) req, (HttpServletResponse) res);
		}
	}

	private void get(HttpServletRequest req, final HttpServletResponse res) throws IOException {
		StringBuffer requestURI = req.getRequestURL();
		URI create = URI.create(requestURI.toString());

		String path = create.getPath();
		String contextPath = getMapping();

		final String transformParams = path.replaceAll(contextPath + "/", "");

		if (path.endsWith(transformParams)) {
			final String[] split = transformParams.split("/");

			String type = split[0];
			String param = split[1];
			String value = split[2];

			TransformationService transformation = TransformationHelper
					.getTransformationService(WebAppActivator.getContext(), type);

			String label;
			if (transformation != null) {
				try {
					label = transformation.transform(param, value);
				} catch (TransformationException e) {
					label = value;
				}
			} else {
				label = value;
			}
			res.getWriter().write(label);
		}
	}

	@Override
	public String getServletInfo() {
		return "Transform Servlet";
	}

	@Override
	public void destroy() {

	}

	@Override
	protected String getServletName() {
		return "transform";
	}

}

package org.openhab.ui.webapp.angular.internal.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.openhab.model.sitemap.Sitemap;
import org.openhab.model.sitemap.SitemapProvider;
import org.openhab.model.sitemap.Widget;
import org.openhab.ui.items.ItemUIRegistry;

public class IconServlet extends BaseServlet {

	private ItemUIRegistry itemUIRegistry;
	private SitemapProvider sitemapProvider;

	public void setItemUIRegistry(ItemUIRegistry itemUIRegistry) {
		this.itemUIRegistry = itemUIRegistry;
	}

	public void unsetItemUIRegistry(ItemUIRegistry itemUIRegistry) {
		this.itemUIRegistry = null;
	}

	public void setSitemapProvider(final SitemapProvider sitemapProvider) {
		this.sitemapProvider = sitemapProvider;
	}

	public void unsetSitemapProvider(final SitemapProvider sitemapProvider) {
		this.sitemapProvider = null;
	}

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

		final String sitemapAndWidget = path.replaceAll(contextPath + "/", "");

		if (path.endsWith(sitemapAndWidget)) {

			final String[] split = sitemapAndWidget.split("/");
			if (split.length == 1) {
				File imgFile = new File("./webapps/images/" + split[0]);
				IOUtils.copy(new FileInputStream(imgFile), res.getOutputStream());
				return;
			}

			Sitemap sitemap = this.sitemapProvider.getSitemap(split[0]);

			Widget widget = this.itemUIRegistry.getWidget(sitemap, split[1]);
			if (widget != null) {
				String icon = itemUIRegistry.getIcon(widget);
				String imagePath = "/images/" + icon + ".png";
				File iconFile = new File("./webapps" + imagePath);
				if (iconFile.exists()) {
					res.sendRedirect(imagePath);
					return;
					// IOUtils.copy(new FileInputStream(iconFile), res.getOutputStream());
				}
			}
		}
		res.sendError(404);
	}

	@Override
	public String getServletInfo() {
		return "Image Servlet";
	}

	@Override
	public void destroy() {

	}

	@Override
	protected String getServletName() {
		return "images";
	}

}

/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package shiver.me.timbers.junit.runner.tomcat.test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;

public class Constants {

    public static final String INIT = "init";
    public static final String PARAM = "param";
    public static final String URL_PATTERN = "/test";
    public static final String SUCCESS = "success";
    public static final String FILTERED = "filtered";

    public static final String SERVLET_NAME = "test-servlet";
    public static final String PACKAGE_SERVLET_NAME = "test-package-servlet";
    public static final String WEB_XML_SERVLET_NAME = "test-webxml-servlet";
    public static final String FILTER_NAME = "test-filter";
    public static final String PACKAGE_FILTER_NAME = "test-package-filter";
    public static final String WEB_XML_FILTER_NAME = "test-webxml-filter";

    public static final String PACKAGE_ONE = "shiver.me.timbers.junit.runner.tomcat.test.one";

    public static final String WEB_XML = "web.xml";

    public static Response GET(int port, String path) {
        return ClientBuilder.newClient()
                .target(String.format("http://localhost:%d/%s", port, path))
                .request(TEXT_PLAIN_TYPE).get();
    }
}

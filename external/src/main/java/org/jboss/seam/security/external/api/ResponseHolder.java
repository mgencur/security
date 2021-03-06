/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.seam.security.external.api;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is used in the SPI to pass the HTTP response on to the
 * application. It also contains methods that make it easier for the application
 * to propagate the dialogue over redirects or postbacks.
 * 
 * @author Marcel Kolsteren
 * 
 */
public interface ResponseHolder
{
   /**
    * Gets the HTTP servlet response
    * 
    * @return the response
    */
   HttpServletResponse getResponse();

   /**
    * Results in a redirect to the specified URL. If a dialogue is active, the
    * id of that dialogue will be appended to the URL as a query parameter, so
    * that the dialogue will be restored when the browser gets the redirect URL.
    * 
    * @param url URL
    */
   void redirectWithDialoguePropagation(String url);

   /**
    * Adds the id of the current dialogue to the URL. If no dialogue is active,
    * it just returns the URL unmodified.
    * 
    * @param url URL
    * @return URL
    */
   String addDialogueIdToUrl(String url);
}

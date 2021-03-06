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
package org.jboss.seam.security.external.saml;

/**
 * @author Marcel Kolsteren
 * 
 */
public class SamlMessage
{
   public static final String QSP_SAML_REQUEST = "SAMLRequest";
   public static final String QSP_SAML_RESPONSE = "SAMLResponse";
   public static final String QSP_RELAY_STATE = "RelayState";

   protected SamlRequestOrResponse samlRequestOrResponse;

   protected String samlMessage;

   protected String relayState;

   public SamlRequestOrResponse getRequestOrResponse()
   {
      return samlRequestOrResponse;
   }

   public void setRequestOrResponse(SamlRequestOrResponse samlRequestOrResponse)
   {
      this.samlRequestOrResponse = samlRequestOrResponse;
   }

   public String getSamlMessage()
   {
      return samlMessage;
   }

   public void setSamlMessage(String samlMessage)
   {
      this.samlMessage = samlMessage;
   }

   public String getRelayState()
   {
      return relayState;
   }

   public void setRelayState(String relayState)
   {
      this.relayState = relayState;
   }
}

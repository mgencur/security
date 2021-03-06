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
package org.jboss.seam.security.external.saml.idp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;

import org.jboss.seam.security.external.saml.api.SamlIdpSession;
import org.jboss.seam.security.external.saml.api.SamlPrincipal;

/**
 * @author Marcel Kolsteren
 * 
 */
@SessionScoped
public class SamlIdpSessions implements Serializable
{
   private static final long serialVersionUID = 6297278286428111620L;

   private Set<SamlIdpSessionImpl> sessions = new HashSet<SamlIdpSessionImpl>();

   public SamlIdpSession addSession(SamlPrincipal principal)
   {
      String sessionIndex;
      int i = 0;
      do
      {
         sessionIndex = Integer.toString(i);
      }
      while (getSession(principal, sessionIndex) != null);

      SamlIdpSessionImpl session = new SamlIdpSessionImpl();
      session.setPrincipal(principal);
      session.setSessionIndex(sessionIndex);
      sessions.add(session);

      return session;
   }

   public SamlIdpSession getSession(SamlPrincipal principal, String sessionIndex)
   {
      for (SamlIdpSessionImpl session : sessions)
      {
         if (session.getPrincipal().equals(principal) && session.getSessionIndex().equals(sessionIndex))
         {
            return session;
         }
      }
      return null;
   }

   public void removeSession(SamlIdpSessionImpl session)
   {
      sessions.remove(session);
   }

   public Set<SamlIdpSessionImpl> getSessions()
   {
      return sessions;
   }
}

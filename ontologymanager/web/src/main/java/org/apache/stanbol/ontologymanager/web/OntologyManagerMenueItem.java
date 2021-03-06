/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.stanbol.ontologymanager.web;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.stanbol.commons.web.base.NavigationLink;

/**
 * The menue item for the Stanbol OntologyManager component
 */
@Component
@Service(value=NavigationLink.class)
public class OntologyManagerMenueItem extends NavigationLink {

    private static final String NAME = "ontonet";

    private static final String htmlDescription = 
            "A <strong>controlled environment</strong> for managing Web ontologies, "+
            "<strong>ontology networks</strong> and user sessions that put them to use.";

	public OntologyManagerMenueItem() {
		super(NAME, "/"+NAME, htmlDescription, 50);
	}
	
}

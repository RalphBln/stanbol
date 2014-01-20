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
package org.apache.stanbol.enhancer.engines.dereference;

import org.apache.clerezza.rdf.core.Language;
import org.apache.clerezza.rdf.core.PlainLiteral;
import org.apache.clerezza.rdf.core.Triple;

/**
 * Define configuration parameters for Dereference engines
 * @author Rupert Westenthaler
 *
 */
public interface DereferenceConstants {
    
    /**
     * Property that allows to enable/disable the filtering of {@link Triple}s
     * with {@link PlainLiteral} {@link Triple#getObject() objects} based on
     * their {@link Language}. Languages that need to be dereferenced are
     * parsed to the {@link EntityDereferencer} via the
     * {@link DereferenceContext#getContentLanguages()}. If empty no languages
     * MUST BE filtered. <p>
     * If both this and {@link #FILTER_ACCEPT_LANGUAGES} are enabled the filter
     * should use the union of the two sets available via 
     * {@link DereferenceContext#getLanguages()}.
     */
    String FILTER_CONTENT_LANGUAGES = "enhancer.engine.dereference.filterContentlanguages";
    /**
     * By default {@link #FILTER_CONTENT_LANGUAGES} is deactivated
     */
    boolean DEFAULT_FILTER_CONTENT_LANGUAGES = false;
    
    /**
     * Property that allows to enable/disable the filtering of {@link Triple}s
     * with {@link PlainLiteral} {@link Triple#getObject() objects} based on
     * their {@link Language}. Languages that need to be dereferenced are
     * parsed to the {@link EntityDereferencer} via the
     * {@link DereferenceContext#getAcceptLanguages()}. If empty no languages
     * MUST BE filtered.<p>
     * If both this and {@link #FILTER_CONTENT_LANGUAGES} are enabled the filter
     * should use the union of the two sets available via 
     * {@link DereferenceContext#getLanguages()}.
     */
    String FILTER_ACCEPT_LANGUAGES = "enhancer.engine.dereference.filterAcceptlanguages";
    
    /**
     * By default {@link #FILTER_ACCEPT_LANGUAGES} is activated
     */
    boolean DEFAULT_FILTER_ACCEPT_LANGUAGES = true;
    
    /**
     * Property used to configure the fields that should be dereferenced.<p>
     * DereferenceEngines need to support a list of URIs but may also support more
     * complex syntax (such as the Entityhub FiedMapping). However parsing a
     * list of properties URIs MUST BE still valid.<p>
     * Support for Namespace prefixes via the Stanbol Namespace Prefix Service
     * is optional. If unknown prefixes are used or prefixes are not supported
     * the Engine is expected to throw a 
     * {@link org.osgi.service.cm.ConfigurationException} during activation
     */
    String DEREFERENCE_ENTITIES_FIELDS = "enhancer.engines.dereference.fields";
    /**
     * Property used to configure LDPath statements. Those are applied using
     * each referenced Entity as Context.<p>
     * DereferenceEngines that can not support LDPath are expected to throw a
     * {@link org.osgi.service.cm.ConfigurationException} if values are set
     * for this property.
     */
    String DEREFERENCE_ENTITIES_LDPATH = "enhancer.engines.dereference.ldpath";

	/**
	 * A URI prefix checked for entity URIs. Only entities that do match any of the
	 * parsed prefixes or {@link #URI_PATTERN} will be dereferenced. If no 
	 * pattern nor prefixes are configured all entities will be dereferenced. 
	 * This has lower priority as {@link #FALLBACK_MODE}.
	 * @see #FALLBACK_MODE
	 */
    String URI_PREFIX = "enhancer.engines.dereference.uriPrefix";
    
    
	/**
	 * Regex pattern applied to entity URIs. Only entities that do match any of
	 * the configured {@link #URI_PREFIX} or pattern will be dereferenced. 
	 * If no pattern nor prefixes are configured all entities will be dereferenced.
	 * This has lower priority as {@link #FALLBACK_MODE}.
	 * @see #FALLBACK_MODE
	 */
    String URI_PATTERN = "enhancer.engines.dereference.uriPattern";
    
    /**
     * If fallback mode is activated a dereference engine will not try to
     * dereference entities for those there are already triples added to the
     * enhancement results.
     */
    String FALLBACK_MODE = "enhancer.engines.dereference.fallback";
    
    boolean DEFAULT_FALLBACK_MODE = true;
    
}

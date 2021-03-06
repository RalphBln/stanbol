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
package org.apache.stanbol.rules.manager.atoms;

import org.apache.stanbol.rules.base.api.RuleAtom;

public class NewIRIAtom implements RuleAtom {

    private IObjectAtom newNodeVariable;
    private StringFunctionAtom binding;

    public NewIRIAtom(IObjectAtom newNodeVariable, StringFunctionAtom nodeName) {
        this.newNodeVariable = newNodeVariable;
        this.binding = nodeName;
    }

    public IObjectAtom getNewNodeVariable() {
        return newNodeVariable;
    }

    public StringFunctionAtom getBinding() {
        return binding;
    }

    @Override
    public String toString() {
        String variable = newNodeVariable.toString();

        String bindingString = null;

        bindingString = binding.toString();

        return "newIRI(" + variable + ", " + bindingString + ")";
    }

    @Override
    public String prettyPrint() {
        return "bind the variable \"" + newNodeVariable.toString() + "\" to the IRI value \""
               + binding.toString() + "\"";
    }
}

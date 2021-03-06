/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.core.impl.score.stream.drools.common.rules;

import org.optaplanner.core.api.function.QuadFunction;
import org.optaplanner.core.api.score.stream.quad.QuadConstraintCollector;
import org.optaplanner.core.impl.score.stream.drools.quad.DroolsQuadToTriGroupByAccumulator;

class QuadGroupBy2Map1CollectMutator<A, B, C, D, NewA, NewB, NewC> extends AbstractQuadGroupByMutator {

    private final QuadFunction<A, B, C, D, NewA> groupKeyMappingA;
    private final QuadFunction<A, B, C, D, NewB> groupKeyMappingB;
    private final QuadConstraintCollector<A, B, C, D, ?, NewC> collectorC;

    public QuadGroupBy2Map1CollectMutator(QuadFunction<A, B, C, D, NewA> groupKeyMappingA,
            QuadFunction<A, B, C, D, NewB> groupKeyMappingB, QuadConstraintCollector<A, B, C, D, ?, NewC> collectorC) {
        this.groupKeyMappingA = groupKeyMappingA;
        this.groupKeyMappingB = groupKeyMappingB;
        this.collectorC = collectorC;
    }

    @Override
    public AbstractRuleAssembler apply(AbstractRuleAssembler ruleAssembler) {
        return groupBiWithCollect(ruleAssembler, () -> new DroolsQuadToTriGroupByAccumulator<>(groupKeyMappingA,
                groupKeyMappingB, collectorC, ruleAssembler.getVariable(0), ruleAssembler.getVariable(1),
                ruleAssembler.getVariable(2), ruleAssembler.getVariable(3)));
    }
}

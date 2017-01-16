/*
 * Copyright 2017 Code Above Lab LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeabovelab.dm.cluman.ds.clusters;

import com.codeabovelab.dm.cluman.cluster.docker.management.DockerService;
import com.codeabovelab.dm.cluman.ds.AbstractContainersManager;
import com.codeabovelab.dm.cluman.model.ContainerService;
import com.codeabovelab.dm.cluman.model.DockerContainer;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

/**
 */
class SmContainersManager extends AbstractContainersManager {

    public SmContainersManager(Supplier<DockerService> supplier) {
        super(supplier);
    }

    @Override
    public Collection<ContainerService> getServices() {
        //TODO getDocker().getServices();
        return Collections.emptyList();
    }

    @Override
    public Collection<DockerContainer> getContainers() {
        //TODO getDocker().getTasks();
        return Collections.emptyList();
    }
}
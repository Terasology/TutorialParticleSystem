/*
 * Copyright 2017 MovingBlocks
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
package org.terasology.TutorialParticleSystem;

import org.terasology.entitySystem.entity.EntityBuilder;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.logic.common.ActivateEvent;
import org.terasology.logic.location.LocationComponent;
import org.terasology.math.geom.Vector3f;
import org.terasology.registry.In;
import org.terasology.world.block.BlockComponent;

@RegisterSystem
public class EmitParticleOnActivateSystem extends BaseComponentSystem{

    @In
    private EntityManager entityManager;

    @ReceiveEvent(components = {EmitParticleOnActivateComponent.class, BlockComponent.class})
    public void onActivate(ActivateEvent event, EntityRef entity) {
        Vector3f loc = entity.getComponent(LocationComponent.class).getWorldPosition();
        Vector3f loc_emitter = loc;
        loc_emitter.setY(loc.getY()+1); // Set the location one block above the activated block
        EntityBuilder builder = entityManager.newBuilder("TutorialParticleSystem:smoke");
        builder.getComponent(LocationComponent.class).setWorldPosition(loc_emitter);
        builder.build(); // Spawn the particle emitter
    }
}

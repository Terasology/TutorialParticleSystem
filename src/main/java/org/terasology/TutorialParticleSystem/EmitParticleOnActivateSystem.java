// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.TutorialParticleSystem;

import org.joml.Vector3f;
import org.terasology.engine.entitySystem.entity.EntityBuilder;
import org.terasology.engine.entitySystem.entity.EntityManager;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.event.ReceiveEvent;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.common.ActivateEvent;
import org.terasology.engine.logic.location.LocationComponent;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.block.BlockComponent;

@RegisterSystem
public class EmitParticleOnActivateSystem extends BaseComponentSystem{

    @In
    private EntityManager entityManager;

    @ReceiveEvent(components = {EmitParticleOnActivateComponent.class, BlockComponent.class})
    public void onActivate(ActivateEvent event, EntityRef entity) {
        Vector3f locEmitter = entity.getComponent(LocationComponent.class).getWorldPosition(new Vector3f()).add(0, 1, 0);
        EntityBuilder builder = entityManager.newBuilder("TutorialParticleSystem:smoke");
        builder.getComponent(LocationComponent.class).setWorldPosition(locEmitter);
        builder.build(); // Spawn the particle emitter
    }
}

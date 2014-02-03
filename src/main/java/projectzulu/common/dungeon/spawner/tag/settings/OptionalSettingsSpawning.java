package projectzulu.common.dungeon.spawner.tag.settings;

import java.util.EnumSet;

import projectzulu.common.dungeon.spawner.tag.keys.Key;

/**
 * Tags system taken from the Just Another Spawner minecraft mod licensed under the Apache license v2.0
 * 
 * Just Another Spawner (JAS) is a Minecraft Mod that aimed at providing an alternative entity spawning system for
 * Minecraft.
 * 
 * License / Legal Stuff Copyright 2012 Tarion Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
public class OptionalSettingsSpawning extends OptionalSettingsBase {

    public OptionalSettingsSpawning(String parseableString) {
        super(parseableString.replace("{", "").replace("}", ""), EnumSet.of(Key.spawn, Key.light, Key.block,
                Key.blockRange, Key.blockFoot, Key.spawnRange, Key.sky, Key.minSpawnHeight, Key.maxSpawnHeight,
                Key.liquid, Key.opaque, Key.normal, Key.solidSide, Key.difficulty, Key.torchLight, Key.ground, Key.top,
                Key.fill, Key.modspawn, Key.origin, Key.players, Key.entities, Key.random, Key.writenbt));
    }
}

@file:Suppress("invisible_reference", "invisible_member")

package net.weavemc.weave.hooks

import CancellableEvent
import MouseEvent
import net.weavemc.loader.api.Hook
import net.weavemc.loader.api.util.asm
import net.weavemc.loader.util.callEvent
import net.weavemc.loader.util.internalNameOf
import net.weavemc.loader.util.named
import org.lwjgl.input.Mouse
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.LabelNode
import org.objectweb.asm.tree.MethodInsnNode

internal class MouseEventHook : Hook("net/minecraft/client/Minecraft") {
    override fun transform(node: ClassNode, cfg: AssemblerConfig) {
        val mn = node.methods.named("runTick")

        val mouseNext = mn.instructions.find {
            it is MethodInsnNode && it.owner == internalNameOf<Mouse>() && it.name == "next"
        }!!

        val top = LabelNode()
        mn.instructions.insertBefore(mouseNext, top)
        mn.instructions.insert(mouseNext.next, asm {
            new(internalNameOf<MouseEvent>())
            dup; dup
            invokespecial(internalNameOf<MouseEvent>(), "<init>", "()V")
            callEvent()

            invokevirtual(internalNameOf<CancellableEvent>(), "isCancelled", "()Z")
            ifne(top)
        })
    }
}

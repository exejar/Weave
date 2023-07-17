package net.weavemc.weave.util


import net.weavemc.loader.api.event.Event
import net.weavemc.loader.api.event.EventBus
import net.weavemc.loader.api.util.InsnBuilder
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Type
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.FieldNode
import org.objectweb.asm.tree.MethodNode
import java.io.FileOutputStream

internal fun List<MethodNode>.named(name: String) = find { it.name == name }!!
internal fun List<FieldNode>.named(name: String) = find { it.name == name }!!

internal inline fun <reified T : Any> internalNameOf(): String = Type.getInternalName(T::class.java)

internal inline fun <reified T : AbstractInsnNode> AbstractInsnNode.next(p: (T) -> Boolean = { true }): T? {
    return generateSequence(next) { it.next }.filterIsInstance<T>().find(p)
}

internal inline fun <reified T : AbstractInsnNode> AbstractInsnNode.prev(p: (T) -> Boolean = { true }): T? {
    return generateSequence(previous) { it.previous }.filterIsInstance<T>().find(p)
}

internal fun ClassNode.dump(file: String) {
    val cw = ClassWriter(0)
    accept(cw)
    FileOutputStream(file).use { it.write(cw.toByteArray()) }
}

internal inline fun <reified T : Any> InsnBuilder.getSingleton() =
    getstatic(internalNameOf<T>(), "INSTANCE", "L${internalNameOf<T>()};")

internal fun InsnBuilder.callEvent() =
    invokestatic(
        internalNameOf<EventBus>(),
        "callEvent",
        "(L${internalNameOf<Event>()};)V"
    )

internal fun InsnBuilder.println() {
    getstatic("java/lang/System", "out", "Ljava/io/PrintStream;")
    swap
    invokevirtual("java/io/PrintStream", "println", "(Ljava/lang/Object;)V")
}
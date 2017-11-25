package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IAssertionGroupType

/**
 * A base type for [IAssertionFormatter] which [canFormat][IAssertionFormatter.canFormat] only
 * [IAssertionGroup]s of one specific [IAssertionGroupType].
 *
 * @param T The [IAssertionGroupType] which the concrete sub class [canFormat][IAssertionFormatter.canFormat].
 *
 * @property clazz The [IAssertionGroupType] which the concrete sub class [canFormat][IAssertionFormatter.canFormat].
 *
 * @constructor A base type for [IAssertionFormatter] which [canFormat][IAssertionFormatter.canFormat] only
 *              [IAssertionGroup]s of one specific [IAssertionGroupType].
 * @param clazz The [IAssertionGroupType] which the concrete sub class [canFormat][IAssertionFormatter.canFormat].
 */
abstract class SingleAssertionGroupTypeFormatter<in T : IAssertionGroupType>(
    private val clazz: Class<T>
) : IAssertionFormatter {

    /**
     * Returns true if the given [assertion] is an [IAssertionGroup] and its [type][IAssertionGroup.type]
     * is [T] or a sub type.
     */
    override final fun canFormat(assertion: IAssertion)
        = assertion is IAssertionGroup && clazz.isAssignableFrom(assertion.type::class.java)

    /**
     * Always throws an [UnsupportedOperationException], because this [IAssertionFormatter] can only format
     * [IAssertionGroup]s.
     *
     * @throws UnsupportedOperationException always!
     */
    override final fun formatNonGroup(assertion: IAssertion, methodObject: AssertionFormatterMethodObject)
        = throw UnsupportedOperationException("supports only ${clazz.name} for which one has to call ${IAssertionFormatter::formatGroup.name}")

    /**
     * Checks whether [assertionGroup] is [T] or a sub type and if so, calls [formatGroupHeaderAndGetChildMethodObject]
     * and uses the resulting child-[AssertionFormatterMethodObject] to format [IAssertionGroup.assertions].
     *
     * If [assertionGroup] is *not* [T] or a sub type, then it throws an [UnsupportedOperationException].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     * @param formatAssertions The function which should be called to format the
     *        [assertions][IAssertionGroup.assertions] of the given [assertionGroup].
     *        It itself expects a function which formats single [IAssertion]s in the context of the given
     *        [assertionGroup].
     *
     * @see [IAssertionFormatter.formatGroup].
     *
     * @throws UnsupportedOperationException if the given [assertionGroup] is not [T] or a sub type of it.
     */
    override final fun formatGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit) = when {
        clazz.isAssignableFrom(assertionGroup.type::class.java) -> formatSpecificGroup(assertionGroup, methodObject, formatAssertions)
        else -> throw UnsupportedOperationException("supports only ${clazz.name}")
    }

    private fun formatSpecificGroup(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject, formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit): Unit {
        val childMethodObject = formatGroupHeaderAndGetChildMethodObject(assertionGroup, methodObject)
        formatGroupAssertions(formatAssertions, childMethodObject)
    }

    /**
     * Formats the group header of the given [assertionGroup] (with [type][IAssertionGroup.type] [T]) -- appends the
     * result to the [sb][AssertionFormatterMethodObject.sb] of the given [methodObject] -- and returns the
     * [AssertionFormatterMethodObject] which shall be used for the [IAssertionGroup.assertions].
     *
     * @param assertionGroup The assertion group which should be formatted.
     * @param methodObject The method object which contains inter alia the [sb][AssertionFormatterMethodObject.sb]
     *        to with the result will be appended.
     *
     * @return The [AssertionFormatterMethodObject] which shall be used for the [IAssertionGroup.assertions].
     */
    protected abstract fun formatGroupHeaderAndGetChildMethodObject(assertionGroup: IAssertionGroup, methodObject: AssertionFormatterMethodObject): AssertionFormatterMethodObject

    /**
     * Formats the [IAssertionGroup.assertions] -- has to call the given [formatAssertions] function in order that
     * the [IAssertionFormatterController] can steer the process.
     *
     * @param formatAssertions The function which should be called to format the
     *        [assertions][IAssertionGroup.assertions] of the given [assertionGroup].
     *        It itself expects a function which formats single [IAssertion]s in the context of the given
     *        [assertionGroup].
     * @param childMethodObject The method object which shall be used to format [IAssertionGroup.assertions] -- contains
     * inter alia the [sb][AssertionFormatterMethodObject.sb] to with the result will be appended.
     */
    protected abstract fun formatGroupAssertions(formatAssertions: (AssertionFormatterMethodObject, (IAssertion) -> Unit) -> Unit, childMethodObject: AssertionFormatterMethodObject)
}
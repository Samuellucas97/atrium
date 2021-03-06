package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents a base class for [AssertionGroup]s which have an empty [description] and [representation].
 *
 * @constructor Represents a base class for [AssertionGroup]s which have an empty [description] and [representation].
 * @param type The type of the group, e.g. [InvisibleAssertionGroupType].
 * @param assertions The assertions of this group.
 */
internal open class EmptyNameAndRepresentationAssertionGroup(
    override val type: AssertionGroupType,
    override val assertions: List<Assertion>
) : AssertionGroup {

    /**
     * [Untranslatable.EMPTY] -- an empty string as [Untranslatable].
     */
    override val description: Translatable = Untranslatable.EMPTY

    /**
     * [Text.EMPTY] -- an empty string as [Text].
     */
    override val representation: Any = Text.EMPTY

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + " " + assertions
    }
}

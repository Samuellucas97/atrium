// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
@file:JvmMultifileClass
@file:JvmName("IterableContainsInAnyOrderOnlyCreatorsKt")
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.basic.contains.addAssertionForAssert
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.kbox.glue
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only the
 * [expected] value.
 *
 * Delegates to `values(expected)`.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.value(expected)",
        "ch.tutteli.atrium.api.fluent.en_GB.value"
    )
)
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.value(expected: E): AssertionPlant<T>
    = values(expected)

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [expected]
 * value as well as the [otherExpected] values (if given) need to be
 * contained in [Iterable] where it does not matter in which order but only as
 * many entries should be returned by the [Iterable] as values defined.
 *
 * @param expected The value which is expected to be contained within the [Iterable].
 * @param otherExpected Additional values which are expected to be contained within [Iterable].
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.values(expected, *otherExpected)",
        "ch.tutteli.atrium.api.fluent.en_GB.values"
    )
)
fun <E , T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>.values(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(this, expected glue otherExpected))

/**
 * Finishes the specification of the sophisticated `contains` assertion where the [Iterable] needs to contain only one
 * entry which holds all assertions created by the given [assertionCreatorOrNull] or is `null` in case
 * [assertionCreatorOrNull] is defined as `null`.
 *
 * Delegates to `entries(assertionCreatorOrNull)`.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking
 *   for has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.entry(asSubExpect(assertionCreatorOrNull))",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.entry"
    )
)
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.entry(assertionCreatorOrNull: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = entries(assertionCreatorOrNull)

/**
 * Finishes the specification of the sophisticated `contains` assertion where an entry needs to be contained in the
 * [Iterable] which holds all assertions [assertionCreatorOrNull] might create or needs to be `null` in case
 * [assertionCreatorOrNull] is defined as `null` -- likewise an entry for each
 * [otherAssertionCreatorsOrNulls] needs to be contained in the [Iterable] where it does not matter in which order the
 * entries appear but only as many entries should be returned by the [Iterable] as assertion creators are defined.
 *
 * Notice, that a first-wins strategy applies which means your assertion creator lambdas -- which kind of serve as
 * identification lambdas -- should be ordered in such a way that the most specific identification lambda appears
 * first, not that a less specific lambda wins. For instance, given a `setOf(1, 2)` you should not search for
 * `entries({ isGreaterThan(0) }, { toBe(1) })` but for
 * `entries({ toBe(1) }, { isGreaterThan(0) })` otherwise
 * `isGreaterThan(0)` matches `1` before `toBe(1)` would match it. As a consequence `toBe(1)` could only match the
 * entry which is left -- in this case `2` -- and of course this would fail.
 *
 * @param assertionCreatorOrNull The identification lambda which creates the assertions which the entry we are looking for
 *   has to hold; or in other words, the function which defines whether an entry is the one we are looking for
 *   or not. In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls Additional identification lambdas which each identify (separately) an entry
 *   which we are looking for (see [assertionCreatorOrNull] for more information).
 *
 * @return An [Expect] for the current subject of the assertion.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from api-cc-en_GB to api-fluent-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.9.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.entries(\n" +
            "asSubExpect(assertionCreatorOrNull),\n" +
            "*otherAssertionCreatorsOrNulls.map { asSubExpect(it) }.toTypedArray()\n" +
            ")",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.domain.builders.migration.asSubExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.entries"
    )
)
fun <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>.entries(
    assertionCreatorOrNull: (Assert<E>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Assert<E>.() -> Unit)?
): AssertionPlant<T>
    = addAssertion(AssertImpl.iterable.contains.entriesInAnyOrderOnlyWithAssert(this, assertionCreatorOrNull glue otherAssertionCreatorsOrNulls))

/**
 * Helper method to simplify adding assertions to the plant which itself is stored in
 * [CharSequenceContains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
internal fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.Builder<E, T, S>.addAssertion(
    assertion: Assertion
): AssertionPlant<T> = addAssertionForAssert(assertion)

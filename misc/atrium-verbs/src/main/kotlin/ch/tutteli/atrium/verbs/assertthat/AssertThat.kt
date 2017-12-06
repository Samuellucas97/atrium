package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT
import ch.tutteli.atrium.verbs.AssertionVerb.ASSERT_THAT_THROWN
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

/**
 * Creates an [IAssertionPlant] for the given [subject].
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlant
 */
fun <T : Any> assertThat(subject: T)
    = AtriumFactory.newReportingPlant(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [IAssertionPlant] for the given [subject] and [IAssertionPlant.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [IAssertion]s are added as a group and usually (depending on
 * the configured [IReporter]) reported as a whole.
 *
 * @return The newly created plant.
 *
 * @see IAtriumFactory.newReportingPlantAndAddAssertionsCreatedBy
 */
fun <T : Any> assertThat(subject: T, assertionCreator: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantAndAddAssertionsCreatedBy(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

/**
 * Creates an [IAssertionPlantNullable] for the given [subject] which might be `null`.
 *
 * @return The newly created plant.
 *
 * @see AtriumFactory.newReportingPlantNullable
 */
fun <T : Any?> assertThat(subject: T)
    = AtriumFactory.newReportingPlantNullable(ASSERT_THAT, subject, AtriumReporterSupplier.REPORTER)

/**
 * Creates an [ThrowableThrownBuilder] for the given function [act] which is expected to throw a [Throwable].
 *
 * @return The newly created [ThrowableThrownBuilder].
 */
fun assertThat(act: () -> Unit)
    = ThrowableThrownBuilder(ASSERT_THAT_THROWN, act, AtriumReporterSupplier.REPORTER)
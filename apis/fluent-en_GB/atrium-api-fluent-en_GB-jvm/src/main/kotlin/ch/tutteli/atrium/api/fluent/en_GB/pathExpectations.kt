package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.kbox.glue
import java.nio.charset.Charset
import java.nio.file.Path
/**
 * Expects that the subject of `this` expectation (a [Path]) is a empty directory;
 * meaning that there is a file system entry at the location the [Path] points to and that is a empty directory.
 *
 * This assertion _resolves_ symbolic links.
 * Therefore, if a symbolic link exists at the location the subject points to, search will continue
 * at the location the link points at.
 *
 * This assertion is not atomic with respect to concurrent file system operations on the paths the assertion works on.
 * Its result, in particular its extended explanations, may be wrong if such concurrent file system operations
 * take place.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.16.0
 */
fun <T : Path> Expect<T>.toBeAnEmptyDirectory(): Expect<T> =
    _logicAppend { toBeAnEmptyDirectory() }

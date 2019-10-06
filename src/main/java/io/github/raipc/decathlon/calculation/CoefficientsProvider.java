package io.github.raipc.decathlon.calculation;

import io.github.raipc.decathlon.schema.Competition;

public interface CoefficientsProvider {
    Coefficients getCoefficients(Competition<?> competition);
}

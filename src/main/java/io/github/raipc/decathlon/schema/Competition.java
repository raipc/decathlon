package io.github.raipc.decathlon.schema;

import java.util.Arrays;
import java.util.List;

import static io.github.raipc.decathlon.schema.Competition.Field.DISCUS_THROW;
import static io.github.raipc.decathlon.schema.Competition.Field.HIGH_JUMP;
import static io.github.raipc.decathlon.schema.Competition.Field.JAVELIN_THROW;
import static io.github.raipc.decathlon.schema.Competition.Field.LONG_JUMP;
import static io.github.raipc.decathlon.schema.Competition.Field.POLE_VAULT;
import static io.github.raipc.decathlon.schema.Competition.Field.SHOT_PUT;
import static io.github.raipc.decathlon.schema.Competition.Track.HURDLES_110M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_100M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_1500M;
import static io.github.raipc.decathlon.schema.Competition.Track.RUNNING_400M;

public interface Competition<T extends Unit<T>> {
    enum Track implements Competition<TrackTimeUnit> {
        RUNNING_100M(TrackTimeUnit.SECONDS),
        RUNNING_400M(TrackTimeUnit.SECONDS),
        HURDLES_110M(TrackTimeUnit.SECONDS),
        RUNNING_1500M(TrackTimeUnit.SECONDS);

        private final TrackTimeUnit trackTimeUnit;

        Track(TrackTimeUnit trackTimeUnit) {
            this.trackTimeUnit = trackTimeUnit;
        }

        @Override
        public TrackTimeUnit getRequiredUnit() {
            return trackTimeUnit;
        }

        @Override
        public EventType getEventType() {
            return EventType.TRACK;
        }
    }

	enum Field implements Competition<DistanceUnit> {
        LONG_JUMP(DistanceUnit.CENTIMETERS),
        SHOT_PUT(DistanceUnit.METERS),
        HIGH_JUMP(DistanceUnit.CENTIMETERS),
        DISCUS_THROW(DistanceUnit.METERS),
        POLE_VAULT(DistanceUnit.CENTIMETERS),
        JAVELIN_THROW(DistanceUnit.METERS);

        private final DistanceUnit distanceUnit;

        Field(DistanceUnit distanceUnit) {
            this.distanceUnit = distanceUnit;
        }

        @Override
        public DistanceUnit getRequiredUnit() {
            return distanceUnit;
        }

        @Override
        public EventType getEventType() {
            return EventType.FIELD;
        }
    }

    T getRequiredUnit();

    EventType getEventType();

    String name();

    static List<Competition<?>> values() {
        return Arrays.asList(
                RUNNING_100M,
                LONG_JUMP,
                SHOT_PUT,
                HIGH_JUMP,
                RUNNING_400M,
                HURDLES_110M,
                DISCUS_THROW,
                POLE_VAULT,
                JAVELIN_THROW,
                RUNNING_1500M);
    }
}

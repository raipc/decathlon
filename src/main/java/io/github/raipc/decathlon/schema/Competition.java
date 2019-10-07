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
        RUNNING_100M(TrackTimeUnit.SECONDS, "100 m"),
        RUNNING_400M(TrackTimeUnit.SECONDS, "400 m"),
        HURDLES_110M(TrackTimeUnit.SECONDS, "110 m hurdles"),
        RUNNING_1500M(TrackTimeUnit.SECONDS, "1500 m");

        private final TrackTimeUnit trackTimeUnit;
        private final String displayName;

        Track(TrackTimeUnit trackTimeUnit, String displayName) {
            this.trackTimeUnit = trackTimeUnit;
            this.displayName = displayName;
        }

        @Override
        public TrackTimeUnit getRequiredUnit() {
            return trackTimeUnit;
        }

        @Override
        public EventType getEventType() {
            return EventType.TRACK;
        }

        @Override
        public String getDisplayName() {
            return displayName;
        }
    }

	enum Field implements Competition<DistanceUnit> {
        LONG_JUMP(DistanceUnit.CENTIMETERS, "Long jump"),
        SHOT_PUT(DistanceUnit.METERS, "Shot put"),
        HIGH_JUMP(DistanceUnit.CENTIMETERS, "High jump"),
        DISCUS_THROW(DistanceUnit.METERS, "Discus throw"),
        POLE_VAULT(DistanceUnit.CENTIMETERS, "Pole vault"),
        JAVELIN_THROW(DistanceUnit.METERS, "Javelin throw");

        private final DistanceUnit distanceUnit;
        private final String displayName;

        Field(DistanceUnit distanceUnit, String displayName) {
            this.distanceUnit = distanceUnit;
            this.displayName = displayName;
        }

        @Override
        public DistanceUnit getRequiredUnit() {
            return distanceUnit;
        }

        @Override
        public EventType getEventType() {
            return EventType.FIELD;
        }

        @Override
        public String getDisplayName() {
            return displayName;
        }
    }

    T getRequiredUnit();

    EventType getEventType();

    String name();

    String getDisplayName();

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

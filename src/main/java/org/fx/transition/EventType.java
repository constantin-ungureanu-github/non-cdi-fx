package org.fx.transition;

public enum EventType {
    LOGIN {
        @Override
        public String toString() {
            return "Login Event";
        }
    },
    NEXT {
        @Override
        public String toString() {
            return "Next Event";
        }
    }
}

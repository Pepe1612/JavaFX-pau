package pack;

public enum TicketType {
    NORMAL {
        @Override
        public String toString() {
            return "normal ticket";
        }
    },
    REDUCED {
        @Override
        public String toString() {
            return "reduced ticket";
        }
    }
}

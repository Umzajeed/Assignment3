
class CharStack {
    private StackNode head;

    public CharStack() {
        this.head = null;
    }

    public void add(char value) {
        StackNode temp = new StackNode(value);
        temp.link = head;
        head = temp;
    }

    public char remove() {
        if (head == null) {
            System.err.println("Stack Underflow");
            return '\0';
        }
        char val = head.value;
        head = head.link;
        return val;
    }

    public char topElement() {
        if (head == null) {
            System.err.println("Stack is empty");
            return '\0';
        }
        return head.value;
    }

    public boolean isVacant() {
        return head == null;
    }
}

class StackNode {
    char value;
    StackNode link;

    StackNode(char value) {
        this.value = value;
        this.link = null;
    }
}

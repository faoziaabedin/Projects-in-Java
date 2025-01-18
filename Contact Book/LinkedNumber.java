public class LinkedNumber{
    private int base; 
    private DLNode<Digit> front;
    private DLNode<Digit> rear;

    public LinkedNumber (String num, int baseNum) {
        this.base = baseNum; 
        if (num.isEmpty()){
            throw new LinkedNumberException("no digits given");  
        }
        DLNode<Digit> rear = null;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            Digit digit = new Digit(Character.digit(c, baseNum)); // Assuming Digit constructor takes an int value
            DLNode<Digit> currentNode = new DLNode<>(digit);

            if (i == 0) {
                front = currentNode; // First node becomes the front
            } else {
                rear.setRear(currentNode); // Link the previous node to the current one
                currentNode.setFront(rear); // Link current node back to previous
            }

            if (i == num.length() - 1) {
                rear = currentNode; // Last node becomes the rear
            }

            rear = currentNode; // Move to the rear node
        }
    }
    public LinkedNumber(int num) {
        this.base = 10; // Decimal number system base

        String numStr = String.valueOf(num); // Convert int num to String

        if (numStr.isEmpty()) {
            throw new LinkedNumberException("no digits given"); // Throw exception if numStr is empty
        }

        DLNode<Digit> rear = null;
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            // Assuming Digit constructor takes an int value representing the digit
            Digit digit = new Digit(Character.digit(c, 10)); 
            DLNode<Digit> currentNode = new DLNode<>(digit);

            if (i == 0) {
                front = currentNode; // First node becomes the front
            } else {
                rear.setRear(currentNode); // Link the previous node to the current one
                currentNode.setFront(rear); // Link current node back to previous
            }

            if (i == numStr.length() - 1) {
                rear = currentNode; // Last node becomes the rear
            }

            rear = currentNode; // Move to the rear node
        }
    }

    public boolean isValidNumber() {
        DLNode<Digit> currentNode = front;
        while (currentNode != null) {
            Digit digit = currentNode.getElement(); // Assuming getElement() retrieves the Digit object from the node
            int digitValue = digit.getValue(); // Assuming getValue() retrieves the int value from the Digit object

            // For bases 2-10, digits must be between 0 and base-1
            if (base >= 2 && base <= 10) {
                if (digitValue < 0 || digitValue >= base) {
                    return false; // Digit is not valid for the base
                }
            }
            // For bases 11-16, digits can include 0-9 and A-F (or a-f)
            else if (base > 10 && base <= 16) {
                // Assuming there's a way to identify if the digit is a letter (A-F or a-f) and its corresponding value
                boolean isLetter = (digitValue >= 10 && digitValue < base);
                if (digitValue < 0 || (!isLetter && digitValue >= 10)) {
                    return false; // Digit is not valid for the base
                }
            } else {
                // If the base is outside the supported range, the number is considered invalid
                return false;
            }

            currentNode = currentNode.getRear(); // Move to the rear node in the list
        }
        return true; // All digits are valid for the base
    }
    public int getBase() {
        return this.base;
    }
    public DLNode<Digit> getFront() {
        return this.front;
    }
    public DLNode<Digit> getRear() {
        return this.rear;
    }
    public int getNumDigits() {
        int count = 0;
        DLNode<Digit> currentNode = this.front;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getRear(); // Assuming getRear() method returns the rear node in the list
        }
        return count;
    }
    public String toString() {
        StringBuilder numberString = new StringBuilder();
        DLNode<Digit> currentNode = this.front;
        
        while (currentNode != null) {
            numberString.append(currentNode.getElement().toString()); // Assuming getElement() returns the Digit and it has a toString method.
            currentNode = currentNode.getRear(); // Assuming getRear() returns the rear node.
        }
        
        return numberString.toString();
    } 
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of LinkedNumber
        if (!(obj instanceof LinkedNumber)) {
            return false;
        }

        // Typecast obj to LinkedNumber so that we can compare data members
        LinkedNumber other = (LinkedNumber) obj;

        // Check if the base is the same for both numbers
        if (this.base != other.base) {
            return false;
        }

        // Check each node's value one by one
        DLNode<Digit> thisCurrent = this.front;
        DLNode<Digit> otherCurrent = other.getFront();

        while (thisCurrent != null && otherCurrent != null) {
            if (!thisCurrent.getElement().equals(otherCurrent.getElement())) {
                return false; // The digits are not the same
            }
            thisCurrent = thisCurrent.getRear();
            otherCurrent = otherCurrent.getRear();
        }

        // If either of the linked lists still has elements, then they are not equal
        return thisCurrent == null && otherCurrent == null;
    }
    public LinkedNumber convert(int newBase) throws LinkedNumberException {
        // Check if the current LinkedNumber is valid
        if (!this.isValidNumber()) {
            throw new LinkedNumberException("cannot convert invalid number");
        }
        // Convert from decimal to new base
        String newBaseNumber = convertFromDecimalToNewBase(decimalValue, newBase);

        // Create a new LinkedNumber object with the number in the new base
        return new LinkedNumber(newBaseNumber, newBase);
    }

    private String convertFromDecimalToNewBase(int decimalValue, int newBase) {
        // Implement conversion from decimal to new base
        // This is a placeholder, actual implementation needed
        return "";
    }
    public void addDigit(Digit digit, int position) {
        int numDigits = getNumDigits(); // Assuming getNumDigits() is a method that returns the count of nodes

        if (position < 0 || position > numDigits + 1) {
            throw new LinkedNumberException("invalid position");
        }

        DLNode<Digit> newNode = new DLNode<>(digit);

        if (position == 0) {
            // Adding after the rear (at the end of the list)
            newNode.setFront(rear);
            if (rear != null) {
                rear.setRear(newNode);
            }
            rear = newNode;
            if (front == null) {
                // List was empty, new node is now the front as well
                front = newNode;
            }
        } else if (position == numDigits + 1) {
            // Adding before the front (at the start of the list)
            newNode.setRear(front);
            if (front != null) {
                front.setFront(newNode);
            }
            front = newNode;
            if (rear == null) {
                // List was empty, new node is now the rear as well
                rear = newNode;
            }
        } else {
            // Adding in the middle of the list
            DLNode<Digit> current = rear;
            for (int i = 0; i < position - 1; i++) { // Find the node after which to insert
                current = current.getFront();
            }
            // Set the new node's rear and previous references
            newNode.setRear(current.getRear());
            newNode.setFront(current);
            // Update the rear node's previous reference
            if (current.getRear() != null) {
                current.getRear().setFront(newNode);
            }
            // Update the current node's rear reference
            current.setRear(newNode);
        }
    }   
}



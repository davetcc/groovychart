package demo


class DemoDescription {
    def className
    def description

    DemoDescription(String demoClassName, String demoDescription) {
	this.className = demoClassName
        this.description = demoDescription
    }

    def getClassName() { return className }
    def getDescription() { return description }

    public String toString() { return description }

}

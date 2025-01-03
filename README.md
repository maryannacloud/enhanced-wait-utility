# Enhanced Wait Utility

The **Enhanced Wait Utility** is a Java library designed to extend Selenium WebDriver's waiting capabilities with reusable, dynamic, and test-friendly methods. It provides advanced waiting functionality, such as polling for custom conditions and waiting for non-UI events, making your Selenium tests more robust and reliable.

---

## Features

- **Dynamic Polling for Custom Conditions**:
  - Wait for elements to stop moving (e.g., during animations or transitions).
  - Wait for elements to have specific text or attributes.
  - Wait for non-UI conditions, such as API responses or database updates.

- **Easy Integration**:
  - Built on top of Selenium's `FluentWait`.
  - Fully customizable timeouts and polling intervals.

- **Clean and Readable Logging**:
  - Uses SLF4J for structured logging, making debugging straightforward.

---

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/<your-username>/enhanced-wait-utility.git

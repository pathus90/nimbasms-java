# nimbasms-java

<div align="center">

[![JitPack](https://jitpack.io/v/nimbasms/nimbasms-java.svg)](https://jitpack.io/#nimbasms/nimbasms-java)
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**Official Java SDK for the Nimba SMS API**

[Documentation](https://docs.nimbasms.com) • [API Reference](https://api.nimbasms.com) • [Partner Portal](https://www.nimbasms.com)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Quick Start](#quick-start)
- [Usage](#usage)
  - [Authentication](#authentication)
  - [Accounts](#accounts)
  - [Messages](#messages)
  - [Contacts](#contacts)
  - [Groups](#groups)
  - [Sender Names](#sender-names)
  - [Purchases](#purchases)
- [Error Handling](#error-handling)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

---

## 🎯 Overview

`nimbasms-java` is the official Java SDK for integrating with the **Nimba SMS API**. This library provides a simple and intuitive interface to send SMS messages, manage contacts, track purchases, and more.

### Why Use This SDK?

- ✅ **Type-safe** - Full Java type safety with comprehensive error handling
- ✅ **Easy to use** - Clean, intuitive API designed for developer productivity
- ✅ **Well documented** - Extensive code examples and documentation
- ✅ **Production ready** - Battle-tested in production environments
- ✅ **Actively maintained** - Regular updates and bug fixes

---

## ✨ Features

- 📤 **Send SMS** - Send single or bulk SMS messages
- 👥 **Contact Management** - Create, update, and organize contacts
- 📊 **Account Information** - Check balance and account details
- 🏷️ **Sender Names** - Manage custom sender IDs
- 📁 **Group Management** - Organize contacts into groups
- 💳 **Purchase History** - Track SMS credit purchases
- 🔄 **Pagination Support** - Navigate through large result sets

---

## 📦 Prerequisites

Before you begin, ensure you have the following:

- **Java**: JDK 11 or higher
- **Build Tool**: Maven or Gradle
- **Nimba SMS Account**: Active subscription via [Nimba SMS Partner Portal](https://www.nimbasms.com)
- **API Credentials**: `ACCOUNT_SID` and `AUTH_TOKEN` (obtain from your dashboard)

---

## 🚀 Installation

### Option 1: Maven Central (Recommended)

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.nimbasms</groupId>
    <artifactId>nimbasms</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Option 2: JitPack

If the package is not yet available on Maven Central, use JitPack:

#### 1. Add JitPack repository

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### 2. Add dependency

**Using latest release tag:**

```xml
<dependency>
    <groupId>com.github.nimbasms</groupId>
    <artifactId>nimbasms-java</artifactId>
    <version>v0.0.1</version>
</dependency>
```

**Using master branch:**

```xml
<dependency>
    <groupId>com.github.nimbasms</groupId>
    <artifactId>nimbasms-java</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

### Gradle

```gradle
dependencies {
    implementation 'com.nimbasms:nimbasms:0.0.1'
}
```

---

## ⚡ Quick Start

```java
import com.nimbasms.nimbasms.NimbaSMSClient;
import com.nimbasms.nimbasms.response.MessageResponse;
import java.util.List;

public class QuickStart {
    public static void main(String[] args) {
        // Initialize client with your credentials
        String ACCOUNT_SID = "your_account_sid";
        String AUTH_TOKEN = "your_auth_token";
        
        NimbaSMSClient client = new NimbaSMSClient(ACCOUNT_SID, AUTH_TOKEN);
        
        // Send your first SMS
        MessageResponse response = client.getMessage().create(
            "YourSenderName",
            List.of("+224XXXXXXXXX"),
            "Hello from Nimba SMS!"
        );
        
        System.out.println("Message sent: " + response);
    }
}
```

---

## 📖 Usage

### Authentication

Create a client instance with your API credentials:

```java
String ACCOUNT_SID = System.getenv("NIMBA_ACCOUNT_SID");
String AUTH_TOKEN = System.getenv("NIMBA_AUTH_TOKEN");

NimbaSMSClient client = new NimbaSMSClient(ACCOUNT_SID, AUTH_TOKEN);
```

> 💡 **Security Tip**: Store credentials in environment variables, not in your code!

---

### Accounts

#### Get Account Information

```java
AccountResponse account = client.getAccount().get();
System.out.println("Account Name: " + account.getName());
System.out.println("Status: " + account.getStatus());
```

#### Check Balance

```java
AccountResponse account = client.getAccount().get();
double balance = account.getBalance();
System.out.println("Current Balance: " + balance + " credits");
```

---

### Messages

#### Send Single SMS

```java
MessageResponse response = client.getMessage().create(
    "MySenderName",
    List.of("+224XXXXXXXXX"),
    "Your message here"
);
```

#### Send Bulk SMS

```java
List<String> recipients = List.of(
    "+224610000001",
    "+224620000002",
    "+224630000003"
);

MessageResponse response = client.getMessage().create(
    "MySenderName",
    recipients,
    "Bulk SMS message"
);
```

#### List All Messages

```java
// Get all messages
MessageResponse messages = client.getMessage().list();

// Get specific number of messages
MessageResponse last10 = client.getMessage().list(10, 0);
```

#### Pagination

```java
// Get first page
MessageResponse page1 = client.getMessage().list(10, 0);

// Navigate to next page
MessageResponse page2 = client.getMessage().next();

// Navigate to previous page
MessageResponse previousPage = client.getMessage().previous();
```

#### Retrieve Message Details

```java
String messageId = "msg_123456";
MessageDetails details = client.getMessage().retrieve(messageId);

System.out.println("Status: " + details.getStatus());
System.out.println("Sent: " + details.getCreatedAt());
```

---

### Contacts

#### Create Contact

```java
// Simple contact
ContactDto contact = client.getContact().create(
    "+224XXXXXXXXX",
    null,
    null
);

// Contact with name and groups
ContactDto contact = client.getContact().create(
    "+224XXXXXXXXX",
    "John Doe",
    List.of("Customers", "VIP")
);
```

#### List Contacts

```java
// Get all contacts
ContactResponse contacts = client.getContact().list();

// Get specific number
ContactResponse last10 = client.getContact().list(10, 0);

// Pagination
ContactResponse nextPage = client.getContact().next();
ContactResponse previousPage = client.getContact().previous();
```

---

### Groups

#### List All Groups

```java
GroupResponse groups = client.getGroup().list();

for (Group group : groups.getData()) {
    System.out.println(group.getName());
}
```

#### Pagination

```java
// Get first 10 groups
GroupResponse page1 = client.getGroup().list(10, 0);

// Navigate pages
GroupResponse nextPage = client.getGroup().next();
GroupResponse previousPage = client.getGroup().previous();
```

---

### Sender Names

#### List Sender Names

```java
SenderNameResponse senderNames = client.getSenderName().list();

// Check approved sender names
for (SenderName name : senderNames.getData()) {
    if (name.isApproved()) {
        System.out.println("Approved: " + name.getName());
    }
}
```

#### Pagination

```java
SenderNameResponse page1 = client.getSenderName().list(10, 0);
SenderNameResponse nextPage = client.getSenderName().next();
SenderNameResponse previousPage = client.getSenderName().previous();
```

---

### Purchases

#### List Purchase History

```java
// Get all purchases
PurchaseResponse purchases = client.getPurchase().list();

// Get specific number
PurchaseResponse recent = client.getPurchase().list(10, 0);
```

#### Pagination

```java
PurchaseResponse page1 = client.getPurchase().list(10, 0);
PurchaseResponse nextPage = client.getPurchase().next();
PurchaseResponse previousPage = client.getPurchase().previous();
```

---

## ⚠️ Error Handling

Always wrap API calls in try-catch blocks:

```java
try {
    MessageResponse response = client.getMessage().create(
        "SenderName",
        List.of("+224XXXXXXXXX"),
        "Test message"
    );
    System.out.println("Success: " + response);
    
} catch (NimbaSMSException e) {
    System.err.println("API Error: " + e.getMessage());
    System.err.println("Error Code: " + e.getErrorCode());
    
} catch (Exception e) {
    System.err.println("Unexpected error: " + e.getMessage());
}
```

---

## 💡 Best Practices

### 1. **Secure Your Credentials**

```java
// ❌ Bad - hardcoded credentials
NimbaSMSClient client = new NimbaSMSClient("AC123...", "xyz789...");

// ✅ Good - use environment variables
String sid = System.getenv("NIMBA_ACCOUNT_SID");
String token = System.getenv("NIMBA_AUTH_TOKEN");
NimbaSMSClient client = new NimbaSMSClient(sid, token);
```

### 2. **Reuse Client Instance**

```java
// Create once, use many times
public class SMSService {
    private final NimbaSMSClient client;
    
    public SMSService() {
        this.client = new NimbaSMSClient(
            System.getenv("NIMBA_ACCOUNT_SID"),
            System.getenv("NIMBA_AUTH_TOKEN")
        );
    }
    
    public void sendWelcome(String phoneNumber) {
        client.getMessage().create("Brand", List.of(phoneNumber), "Welcome!");
    }
}
```

### 3. **Validate Phone Numbers**

```java
public boolean isValidGuineaNumber(String phone) {
    // Guinea numbers: +224 followed by 9 digits
    return phone.matches("^\\+224[0-9]{9}$");
}
```

### 4. **Handle Pagination Efficiently**

```java
public void processAllMessages() {
    MessageResponse response = client.getMessage().list(50, 0);
    
    do {
        response.getData().forEach(this::processMessage);
        
        if (response.getNext() != null) {
            response = client.getMessage().next();
        } else {
            break;
        }
    } while (true);
}
```

### 5. **Monitor Your Balance**

```java
public void checkBalanceBeforeSending(int messageCount) {
    AccountResponse account = client.getAccount().get();
    double balance = account.getBalance();
    
    if (balance < messageCount) {
        throw new IllegalStateException(
            "Insufficient balance. Required: " + messageCount + 
            ", Available: " + balance
        );
    }
}
```

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

### Development Setup

```bash
# Clone the repository
git clone https://github.com/nimbasms/nimbasms-java.git
cd nimbasms-java

# Build the project
mvn clean install

# Run tests
mvn test
```

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

---

## 🆘 Support

Need help? We're here for you!

- 📧 **Email**: support@nimbasms.com
- 📚 **Documentation**: [docs.nimbasms.com](https://docs.nimbasms.com)
- 🌐 **Website**: [www.nimbasms.com](https://www.nimbasms.com)
- 💬 **Issues**: [GitHub Issues](https://github.com/nimbasms/nimbasms-java/issues)

---

## 🔗 Links

- [Official Website](https://www.nimbasms.com)
- [API Documentation](https://developers.nimbasms.com/)
- [Partner Portal](https://www.nimbasms.com/partner)
- [Status Page](https://status.nimbasms.com)

---

<div align="center">

**Crafted with ❤️ by [Mamadou Pathé DIALLO](https://github.com/pathus90)**

*Proud contributor to Nimba SMS*

[⬆ Back to Top](#nimbasms-java)

</div>

</div>

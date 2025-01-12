# nimbasms-java
A Java module for communicating with Nimba SMS API. 

## Usage
First, instantiate the client using your API key:

- [Installation](#installation)
- [Get your Access token](#accesToken)
- [Accounts](#account)
- [Groups](#group)
- [Sendernames](#sendername)
- [Contacts](#contact)
- [Message](#message)
## <a name="installation"></a> Installation

### System Requirements
- JDK 11 or higher.
- subscription via Nimba SMS Partner portal

### Add Maven Dependency
If you use Maven, add the following configuration to your project's `pom.xml`
```maven
<dependency>
  <groupId>com.nimbasms</groupId>
  <artifactId>nimbasms</artifactId>
  <version>0.0.1</version>
</dependency>
```
Avant d'instancier un objet `NimbaSMSClient`, assurez-vous d'avoir les identifiants requis. Ces identifiants (ACCOUNT_SID et AUTH_TOKEN) doivent être obtenus auprès de `NimbaSMS`.

## <a name="accesToken"></a> Get your Access token
```java

NimbaSMSClient client = new NimbaSMSClient("ACCOUNT_SID", "AUTH_TOKEN")
```

## <a name="account"></a> Accounts
Retrieve the account information using the getAccount() method:
```java
AccountResponse account = client.getAccount().get();
System.out.println(account);
```
Check balance
```java
AccountResponse account = client.getAccount().get();
System.out.println(account.getBalance());
```

## <a name="group"></a> Groups
This code retrieves a list of all groups.
```java
GroupResponse groups = client.getGroup().list();
System.out.println(groups);
```

You can also retrieve the last 10 Group by passing in the limit and offset:
```java
GroupResponse last10groups = client.getGroup().list(10, 1);
System.out.println(last10groups);
```
The next method returns the next item in a list.
```java
GroupResponse nextGroups = client.getGroup().next();
System.out.println(nextGroups);
```
The previous method returns the previous item in the list.
```java
GroupResponse previousGroups = client.getGroup().previous();
System.out.println(previousGroups);
```

## <a name="sendername"></a> Sendernames
Retrieve the sender names using the getSenderName() method:

```java
SenderNameResponse senderNames = client.getSenderName().list();
System.out.println(senderNames);
```
You can also retrieve the last 10 sender names by passing in the limit and offset:

```java
SenderNameResponse last10SenderNames = client.getSenderName().list(10, 1);
System.out.println(last10SenderNames);
```

The next method returns the next item in a list.

```java
SenderNameResponse nextSenderNames = client.getSenderName().next();
System.out.println(nextSenderNames);
```
The previous method returns the previous item in the list.

```java
SenderNameResponse previousSenderNames = client.getSenderName().previous();
System.out.println(previousSenderNames);
```

## <a name="contact"></a> Contacts
This code retrieves a list of all contacts.
```java
ContactResponse contacts = client.getContact().list();
System.out.println(contacts);
```
You can also retrieve the last 10 contacts by passing in the limit and offset:
```java
ContactResponse last10contacts = client.getContact().list(10, 1);
System.out.println(last10contacts);
```
The next method returns the next item in a list.
```java
ContactResponse nextContacts = client.getContact().next();
System.out.println(nextContacts);
```
The previous method returns the previous item in the list.
```java
ContactResponse previousContacts = client.getContact().previous();
System.out.println(previousContacts);
```

Create Contact. This contact will be added to the default contact list:
```java
ContactDto createContactResponse = client.getContact().create("+224XXXXXXXXX", null, null);
System.out.println(createContactResponse)
```
Create with groups and name - name and groups are optional.
```java
ContactDto contactResponseWithGroupsAndName = client.getContact().create("+224XXXXXXXXX", "Foo", List.of("API", "Facebook Client"));
System.out.println(contactResponseWithGroupsAndName);
```

## <a name="message"></a> Messages
Get All messages
```java
MessageResponse messages = client.getMessage().list();
System.out.println(messages);
```
Get only last 10 messages
```java
MessageResponse last10Messages = client.getMessage().list(10, 1);
System.out.println(last10Messages);
```
The next method returns the next item in a list.
```java
MessageResponse nextMessages = client.getMessage().next();
System.out.println(nextMessages);
```
The previous method returns the previous item in the list.
```java
MessageResponse previousMessages = client.getMessage().previous();
System.out.println(previousMessages);
```

Send a message
```java
MessageResponse messageResponse = client.getMessage().create("sender_name", List.of("+224XXXXXXXXX"), "Hello nimba");
System.out.println(messageResponse);
```
Retrieve message
```java
MessageDetails messageDetails = client.getMessage().retrieve("123");
System.out.println(messageDetails);
```


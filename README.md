# DataService

An abstraction layer (interface) for portability of NoSQL solutions across multiple datastores

Currently supported are
* [OfyDataService](https://github.com/fratuz610/OfyDataService)
* [MorphiaDataService](https://github.com/fratuz610/MorphiaDataService)
* [TinyDBDataService](https://github.com/fratuz610/TinyDB)

## Features

* Allows to code once, deploy to many/different environments
    * Quite useful for moving between Google Application Engine datastore and a MongoDB datastore with minimal issues

## Dependencies

 * None

## Usage

### 1 - Define your pojos according to the implementation requirements (they vary)

    public static class Message {
        @Id public Long messageId;
        @Indexed public String author;
        public String message;
        @Indexed public long timestamp = new Date().getTime();
    }

### 2 - Enjoy a unified querying interface

    // We create a connection to the database
    DataService dataService = new OfyDataService();

    // We create an object

    Message mess = new Message();
    
    // we start adding it to the db
    dataService.put(mess);

    // we retrieve the objects we are interested in

    // we want all objects of type 'Message'
    List<Message> messageList = dataService.getList(Message.class);

    // we want objects with a with a specific field value
    messageList = dataService.getList("author", "myself", Message.class);

    // which is equivalent to
    messageList = dataService.getList("author =", "myself", Message.class);
    // or
    messageList = dataService.getList("author ==", "myself", Message.class);

    // but we could try
    messageList = dataService.getList("timestamp !=", 123l, Message.class);
    messageList = dataService.getList("timestamp >", 123l, Message.class);
    messageList = dataService.getList("timestamp >=", 123l, Message.class);
    messageList = dataService.getList("timestamp <=", 123l, Message.class);
    messageList = dataService.getList("timestamp <", 123l, Message.class);

    // for more generic queries with sorting
    messageList = dataService.getList(new Query()
          .filter("key", "value")
          .filter("anotherKey <", 1234)
          .orderBy("orderKey") // defaults to ascending
          .limit(10)
          .skip(1),Message.class);

    // or with descending ordering
    messageList = dataService.getList(new Query()
          .filter("key", "value")
          .filter("anotherKey <", 1234)
          .orderBy("orderKey", OrderType.DESCENDING) // defaults to ascending
          .limit(10)
          .skip(1),Message.class);

    // to delete an object (based on the 'id' field)
    dataService.delete(mess);

    // to delete multiple objects (based on the '_id' field)
    dataService.deleteAll(messageList);

    // to delete based on a query
    dataService.deleteAll(new Query().filter("key", "value"), Message.class);
    
    // to drop all objects of a class
    dataService.deleteAll(Message.class);

    // to do count() queries
    dataService.getResultSetSize("key", "value", Message.class);
    dataService.getResultSetSize(new Query().filter("key", "value"), Message.class);
    dataService.getResultSetSize(Message.class);

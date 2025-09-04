Folder layout:

your-pos-project/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── yourcompany/
│   │   │           └── pos/
│   │   │               ├── data/
│   │   │               │   └── entities/
│   │   │               │       ├── Product.java
│   │   │               │       ├── Customer.java
│   │   │               │       ├── CartItem.java
│   │   │               │       ├── Cart.java
│   │   │               │       └── Sale.java
│   │   │               ├── services/
│   │   │               │   └── InventoryManager.java
│   │   │               ├── controllers/
│   │   │               │   └── PosController.java
│   │   │               └── dao/
│   │   │                   └── (DAO classes would go here)
│   │   ├── resources/
│   │   │   └── (e.g., config.properties, database.xml)
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       ├── (JSP files, HTML files)
│   │       └── (CSS, JS, images)
│   └── test/
│       └── java/
│           └── com/
│               └── yourcompany/
│                   └── pos/
│                       └── (Test classes would go here)
└── target/
    ├── (compiled .class files, .war file)
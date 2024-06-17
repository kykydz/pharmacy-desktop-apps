# Pharmacy Store App
The goal of this project is to showcase Java GUI application development 
in order to implement a Pharmacy Store to manage its medication stock and transaction stock.

Specification of this apps:
1. `Java SDK 22` as main package
2. `Maven` framework
3. `JavaFX 22` as GUI Library
4. `FXML` as design UI

## Prerequisite Knowledge
1. Basic Java Utility
2. Multiform management
3. Stage management
4. Callback (Transfer data between form/scene)
5. Database connection (PostgresSQL)

# Domain

There are 3 domain related to this project, which then is translated to entities:
- `stock`: represent the stock of medication
- `customer`: represent the customer who will buy the product
- `transaction`: represent the transaction happen related to whi the customer and what items the bought
- `transaction_item`: represent the child of `transaction` record which hold item bought

# Use Case

Below is the following pseudocode for this app in "_happy path_" happening:
1. Admin create `customer` record
2. Admin create `stock` record
3. Transaction is happening by getting `customer`, find available `stock` record.
4. Record new `transaction` and new records of `transaction_item`
5. Update `stock`

Copyright (c) 2024 @kykydz

This project is licensed under the MIT License (https://opensource.org/license/mit).
In short, this license allows you to freely use, modify, and distribute this code for any purpose, with attribution.
We welcome contributions! If you have a bug fix or improvement, please consider creating a pull request.

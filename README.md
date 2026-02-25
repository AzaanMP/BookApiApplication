# BookApiApplication

## Features
* **Full CRUD**: Create, Read, Update, and Delete books.
* **Partial Updates**: Modify specific fields using HTTP PATCH.
* **Advanced Search**: A unified endpoint for filtering by title/author, sorting by specific fields, and paginating results.

---

## Endpoints & Testing Results

### 1. Update Book (PUT)
Replaces an existing book's details entirely.
* **URL**: `PUT http://localhost:8080/api/books/1`
* **Result**:

![PUT Update](https://github.com/AzaanMP/BookApiApplication/blob/34c63c3e9682238c41b93937e82764f38245bae1/screenshots/CPSC%20449%20Homework%201%20Put.png)

---

### 2. Partial Update (PATCH)
Updates only the provided fields (e.g., updating just the price).
* **URL**: `PATCH http://localhost:8080/api/books/1`
* **Result**:

![PATCH Update](https://github.com/AzaanMP/BookApiApplication/blob/34c63c3e9682238c41b93937e82764f38245bae1/screenshots/CPSC%20449%20Homework%201%20Patch.png)

---

### 3. Remove Book (DELETE)
Deletes a book from the collection by its ID.
* **URL**: `DELETE http://localhost:8080/api/books/1`
* **Result**:

![DELETE Book](https://github.com/AzaanMP/BookApiApplication/blob/34c63c3e9682238c41b93937e82764f38245bae1/screenshots/CPSC%20449%20Homework%201%20Delete.png)

---

### 4. Pagination (GET)
Retrieves a specific "slice" of the book collection.
* **URL**: `GET http://localhost:8080/api/books/page?page=0&size=5`
* **Result**:

![Pagination Test (1st image)](https://github.com/AzaanMP/BookApiApplication/blob/34c63c3e9682238c41b93937e82764f38245bae1/screenshots/CPSC%20449%20Homework%201%20Get%20(1).png)
![Pagination Test (2nd image)](https://github.com/AzaanMP/BookApiApplication/blob/34c63c3e9682238c41b93937e82764f38245bae1/screenshots/CPSC%20449%20Homework%201%20Get%20(2).png)

---

### 5. Advanced Search (Unified GET)
Combines filtering, sorting, and pagination in a single request. 
**Logic Order**: Filter ➔ Sort ➔ Paginate.
* **URL**: `GET http://localhost:8080/api/books/advanced?author=Robert Martin&sortBy=title&direction=desc&page=0&size=2`
* **Result**:

![Advanced Search](https://github.com/AzaanMP/BookApiApplication/blob/0e65e5ecb5ba88f8e091f90b04ebdce0ea8a840a/screenshots/CPSC%20449%20Homework%201%20Advanced%20Get.png)

# BookApiApplication

## Features
* **Full CRUD**: Create, Read, Update, and Delete books.
* **Partial Updates**: Modify specific fields using HTTP PATCH.
* **Advanced Search**: A unified endpoint for filtering by title/author, sorting by specific fields, and paginating results.

---

## Endpoints & Testing Results

### 1. Update Book (PUT)
Replaces an existing book's details entirely.
* **URL**: `PUT /api/books/1`
* **Result**: 
![PUT Update](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Put.png)

---

### 2. Partial Update (PATCH)
Updates only the provided fields (e.g., updating just the price).
* **URL**: `PATCH /api/books/1`
* **Result**: 
![PATCH Update](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Patch.png)

---

### 3. Remove Book (DELETE)
Deletes a book from the collection by its ID.
* **URL**: `DELETE /api/books/3`
* **Result**: 
![DELETE Book](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Delete.png)

---

### 4. Pagination (GET)
Retrieves a specific "slice" of the book collection.
* **URL**: `GET /api/books?page=0&size=5`
* **Result**: 
![Pagination Test (1st image)](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Get%20(1).png)]
![Pagination Test (2nd image)](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Get%20(2).png)

---

### 5. Advanced Search (Unified GET)
Combines filtering, sorting, and pagination in a single request. 
**Logic Order**: Filter ➔ Sort ➔ Paginate.
* **URL**: `GET /api/books?title=Java&sortBy=author&page=0&size=2`
* **Result**: 
![Advanced Search](https://github.com/AzaanMP/BookApiApplication/blob/4d6f4a649f4f0f3299515bcd435f6ac2271b3898/screenshots/CPSC%20449%20Homework%201%20Advanced%20Get.png)

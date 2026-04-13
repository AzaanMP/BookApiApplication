# BookApiApplication

Assignment 1: Building on Books API: Advanced Endpoints & Features
## Features
* **Full CRUD**: Create, Read, Update, and Delete books.
* **Partial Updates**: Modify specific fields using HTTP PATCH.
* **Advanced Search**: A unified endpoint for filtering by title/author, sorting by specific fields, and paginating results.

---

## Assignment 1 Endpoints & Testing Results

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

---

## How to Run Assignment 1
1. Clone the repository to your local machine.
2. Open the project on IntelliJ.
3. Ensure you have Maven installed and the dependencies are loaded.
4. Run the `BookApiApplication.java` file.
5. Use Postman or a browser to access the endpoints at `http://localhost:8080`.

---

# Assignment 2: JWT Role-Based Authorization

## Features
* **User Registration**: Register new users with specific roles (e.g., USER, ADMIN).
* **JWT Authentication**: Generate JSON Web Tokens upon successful login to securely authenticate subsequent requests.
* **Role-Based Access Control (RBAC)**: Secure specific endpoints based on user roles (e.g., restricting the DELETE operation to ADMIN users only).

---
## Assignment 2 Endpoints & Testing Results

### 1. Register ADMIN User (POST)
Registers a new user with both `USER` and `ADMIN` roles.
* **URL**: `POST http://localhost:8080/api/auth/register`
* **Result**: Returns a `200 OK` status and a success message.

![Register ADMIN](https://github.com/AzaanMP/BookApiApplication/blob/7f73f193c8bf4ac29d847411583c0565527e9d99/screenshots/RegisterADMIN.png)

---

### 2. Login as ADMIN & Get Token (POST)
Authenticates the user credentials and returns a generated JWT token.
* **URL**: `POST http://localhost:8080/api/auth/login`
* **Result**: Returns a `200 OK` status with the JWT token in the response body.

![Login ADMIN](https://github.com/AzaanMP/BookApiApplication/blob/7f73f193c8bf4ac29d847411583c0565527e9d99/screenshots/AdminLoginTOKEN.png)

---

### 3. DELETE Request as ADMIN (Must Pass)
Tests if an authenticated user with the `ADMIN` role can successfully delete a book. The JWT is passed in the `Authorization` header as a Bearer Token.
* **URL**: `DELETE http://localhost:8080/api/books/1`
* **Result**: Returns a `200 OK` status with the message "Book deleted successfully".

![DELETE as ADMIN](https://github.com/AzaanMP/BookApiApplication/blob/d0a8ee62e9ca11379e59a1c6c9c957bab6d07312/screenshots/Assignment-2-Screenshots/Admin%20Delete%20Book.png)

---

### 4. Repeat Steps 1 and 2 for User
![Register USER](https://github.com/AzaanMP/BookApiApplication/blob/7f73f193c8bf4ac29d847411583c0565527e9d99/screenshots/RegisterUSER.png)

![Login USER](https://github.com/AzaanMP/BookApiApplication/blob/7f73f193c8bf4ac29d847411583c0565527e9d99/screenshots/UserLoginTOKEN.png)

---

### 5. DELETE Request as USER (Must Fail)
Tests role-based authorization by attempting to delete a book using a token from a newly registered user who *only* has the `USER` role.
* **URL**: `DELETE http://localhost:8080/api/books/2`
* **Result**: Returns a `403 Forbidden` status, proving the endpoint is successfully protected against non-admins.

![DELETE as USER](https://github.com/AzaanMP/BookApiApplication/blob/7f73f193c8bf4ac29d847411583c0565527e9d99/screenshots/UserDeleteBook.png)

## How to Run Assignment 2

**Important Note on Data Storage:** This project utilizes in-memory Java Lists to store both Books and Users rather than an external database. **All data resets every time the application is restarted.** If you restart the server, you must re-register your users and generate new JWT tokens!

1. **Start the Application:** Open the project in IntelliJ, ensure Maven dependencies are loaded, and run the `BookApiApplication.java` main class. The server will start on `http://localhost:8080`.

2. **Register Test Users:**
   * Open Postman and send a `POST` request to `http://localhost:8080/api/auth/register`.
   * In the Body (raw JSON), create an Admin user (`"roles": ["USER", "ADMIN"]`) and a standard user (`"roles": ["USER"]`). Make sure the Authorization tab is set to **No Auth**.

3. **Generate JWT Tokens:**
   * Send a `POST` request to `http://localhost:8080/api/auth/login` with the credentials of the user you want to test.
   * Copy the generated JWT string from the response body.

4. **Test the Protected DELETE Endpoint:**
   * Set up a `DELETE` request to `http://localhost:8080/api/books/{id}` (e.g., ID 1).
   * Navigate to the **Authorization** tab, select **Bearer Token**, and paste your copied JWT.
   * Send the request to verify that the Admin token successfully deletes the book (`200 OK`) and the standard User token gets rejected (`403 Forbidden`).

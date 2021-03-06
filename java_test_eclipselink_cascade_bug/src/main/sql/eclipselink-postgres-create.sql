CREATE TABLE books (id  SERIAL NOT NULL, PRIMARY KEY (id))
CREATE TABLE book_to_bookstore (id  SERIAL NOT NULL, book_id INTEGER, bookstore_id INTEGER, PRIMARY KEY (id))
CREATE TABLE bookstores (id  SERIAL NOT NULL, PRIMARY KEY (id))
ALTER TABLE book_to_bookstore ADD CONSTRAINT FK_book_to_bookstore_book_id FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE
ALTER TABLE book_to_bookstore ADD CONSTRAINT FK_book_to_bookstore_bookstore_id FOREIGN KEY (bookstore_id) REFERENCES bookstores (id)

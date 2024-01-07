CREATE TABLE product (
                         id TEXT PRIMARY KEY UNIQUE NOT NULL,
                         title TEXT NOT NULL,
                         slug TEXT NOT NULL,
                         description TEXT NOT NULL,
                         image TEXT NOT NULL,
                         price INTEGER NOT NULL
);

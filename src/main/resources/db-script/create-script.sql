
USE my_db_example;

CREATE TABLE IF NOT EXISTS users (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(45),
  last_name VARCHAR(45) ,
  email VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(45),
  date_of_birth VARCHAR(45)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

  CREATE TABLE IF NOT EXISTS roles (
  id   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
) ENGINE = InnoDB;


  CREATE TABLE IF NOT EXISTS events (
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL UNIQUE,
  base_price DOUBLE,
  rating VARCHAR(45)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

  CREATE TABLE IF NOT EXISTS tickets (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  user_id INT (11),
  event_id INT (11) ,
  date_time VARCHAR(45),
  seat INT(11),
  base_price DOUBLE,
  FOREIGN KEY (user_id)  REFERENCES users (id),
  FOREIGN KEY (event_id)  REFERENCES events (id)
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;

  CREATE TABLE IF NOT EXISTS user_discount_counts (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL UNIQUE,
  count_tenth_ticket_discount INT(11),
  count_birthday_discount INT(11)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

      CREATE TABLE IF NOT EXISTS event_counts (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL UNIQUE,
  count_get_by_name INT(11),
  count_book_tickets INT(11),
  count_get_price INT(11)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

      CREATE TABLE IF NOT EXISTS air_dates (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  event_date VARCHAR(45) NOT NULL UNIQUE
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE IF NOT EXISTS event_dates (
    event_id int(11),
    air_date_id int(11),
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE CASCADE,
    FOREIGN KEY (air_date_id) REFERENCES air_dates (id) ON DELETE CASCADE
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;




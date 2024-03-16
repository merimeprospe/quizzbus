
-- Supprime toutes les données

DELETE FROM compte;


-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email, flagadmin ) VALUES 
( 1, 'geek', 'geek', 'geek@jfox.fr', TRUE ),
( 2, 'job', 'job', 'job@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 3;


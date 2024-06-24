INSERT INTO movie (movie_name, duration, genre) VALUES
                                                    ('THE EXPERTS', 121, 'Action, Crime'),
                                                    ('HOW TO MAKE MILLIONS BEFORE GRANDMA DIES', 127, 'Crime, Drama'),
                                                    ('THE GARFIELD MOVIE', 101, 'Family, Comedy'),
                                                    ('VINA: SEBELUM 7 HARI', 160, 'Horror, Drama'),
                                                    ('TAROT', 92, 'Horror, Comedy'),
                                                    ('KINGDOM OF THE PLANET OF THE APES', 145, 'Sci-fi, Action'),
                                                    ('INSIDE OUT 2', 95, 'Family, Comedy'),
                                                    ('EXHUMA', 134, 'Horror, Mystery'),
                                                    ('JUJUTSU KAISEN 0', 105, 'Action, Fantasy'),
                                                    ('NO WAY UP', 95, 'Thriller, Action');

-- to insert halls associated with movies
INSERT INTO hall (hall_name, movie_id) VALUES
                                           ('Hall A', 1),    -- Assuming 'THE EXPERTS' has ID 1
                                           ('Hall B', 2),    -- Assuming 'HOW TO MAKE MILLIONS BEFORE GRANDMA DIES' has ID 2
                                           ('Hall C', 3),    -- Assuming 'THE GARFIELD MOVIE' has ID 3
                                           ('Hall D', 4),    -- Assuming 'VINA: SEBELUM 7 HARI' has ID 4
                                           ('Hall E', 5),    -- Assuming 'TAROT' has ID 5
                                           ('Hall F', 6),    -- Assuming 'KINGDOM OF THE PLANET OF THE APES' has ID 6
                                           ('Hall G', 7),    -- Assuming 'INSIDE OUT 2' has ID 7
                                           ('Hall H', 8),    -- Assuming 'EXHUMA' has ID 8
                                           ('Hall I', 9),    -- Assuming 'JUJUTSU KAISEN 0' has ID 9
                                           ('Hall J', 10);   -- Assuming 'NO WAY UP' has ID 10


-- Insert data into the 'service_activation' table
INSERT INTO service_activation (category, description, service_type, state) VALUES
('category1', 'Description for service activation 1', 'Type1', 'active'),
('category2', 'Description for service activation 2', 'Type2', 'inactive');

-- Insert data into the 'service_characteristic' table
--INSERT INTO service_characteristic (name, value_type, value, service_activation_id) VALUES
--('activityCd', 'string', 'NAC', 1),
--('billingAccountNumber', 'string', '681459', 1),
--('externalId', 'string', '36274567', 2);

-- Insert data into the 'feature' table
INSERT INTO feature (name, service_activation_id) VALUES
('Feature1', 1),
('Feature2', 2);

-- Insert data into the 'feature_characteristic' table
INSERT INTO feature_characteristic (name, value_type, feature_id) VALUES
('Characteristic1', 'string', 1),
('Characteristic2', 'date', 2);

-- Insert data into the 'offer' table
INSERT INTO offer (offer_id, effective_date, expiry_date, offer_cd, offer_sequence_number, offer_type_cd, name, value_type, feature_characteristic_id) VALUES
('Offer1', '2024-08-01', '2025-08-01', 'CD1', 'Seq1', 'Type1', 'Offer Name 1', 'string', 1),
('Offer2', '2024-08-01', '2025-08-01', 'CD2', 'Seq2', 'Type2', 'Offer Name 2', 'string', 2);

-- Insert data into the 'offer_parameter' table
INSERT INTO offer_parameter (parameter_cd, parameter_name, parameter_effective_date, parameter_expiry_date, parameter_value_tx, offer_id) VALUES
('Param1', 'Parameter Name 1', '2024-08-01', '2025-08-01', 'Value1', 1),
('Param2', 'Parameter Name 2', '2024-08-01', '2025-08-01', 'Value2', 2);

-- Insert data into the 'related_party' table
INSERT INTO related_party (id_value, role, referred_type, service_activation_id) VALUES
('ID1', 'Role1', 'Type1', 1),
('ID2', 'Role2', 'Type2', 2);

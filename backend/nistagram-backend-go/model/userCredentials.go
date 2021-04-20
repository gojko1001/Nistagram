package model

type UserCredentials struct{
	identifiable
	Username string
	Password string
	Salt string
}

func NewUserCredentials(username string, password string, salt string) *UserCredentials {
	return &UserCredentials{
		Username: username,
		Password: password,
		Salt: salt,
	}
}

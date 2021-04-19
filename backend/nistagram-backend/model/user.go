package model

import "time"

type User struct {
	identifiable
	FullName,
	Email,
	Phone,
	Username,
	WebSite,
	Bio string
	BirthDate time.Time
	UserGender Gender
	UserRole Role
	PublicProfile,
	PublicDM,
	Tag bool
}

func NewUser(fullName string, email string, phone string, username string,
	webSite string, bio string, birthDate time.Time, userGender Gender, userRole Role,
	publicProfile bool, publicDM bool, tag bool) *User {
	return &User{
		FullName:      fullName,
		Email:         email,
		Phone:         phone,
		Username:      username,
		WebSite:       webSite,
		Bio:           bio,
		BirthDate:     birthDate,
		UserGender:    userGender,
		UserRole:      userRole,
		PublicProfile: publicProfile,
		PublicDM: publicDM,
		Tag: tag,
	}
}
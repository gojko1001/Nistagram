package model

type Identifiable interface {
	GetId() int64
	SetId(id int64)
}

type identifiable struct {
	id int64
}

func (m *identifiable) SetId(id int64) {
	m.id = id
}
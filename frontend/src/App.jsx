import { useState, useEffect } from 'react'
import './App.css'
import { getAllRecords, addRecord, deleteRecord } from './api'

function App() {
  const [records, setRecords] = useState([])
  const [newRecord, setNewRecord] = useState({
    artist: '',
    title: '',
    genre: '',
    medium: '',
    year: '',
    favourite: false
  })

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords = async () => {
    try {
      const response = await getAllRecords()
      setRecords(response.data)
    } catch (error) {
      console.error('Error fetching records:', error)
    }
  }

  const handleInputChange = (e) => {
    const { name, value, type, checked } = e.target
    setNewRecord({
      ...newRecord,
      [name]: type === 'checkbox' ? checked : value
    })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const recordToSubmit = {
        ...newRecord,
        year: parseInt(newRecord.year) || 0
      }
      await addRecord(recordToSubmit)
      setNewRecord({
        artist: '',
        title: '',
        genre: '',
        medium: '',
        year: '',
        favourite: false
      })
      fetchRecords()
    } catch (error) {
      console.error('Error adding record:', error)
    }
  }

  const handleDelete = async (id) => {
    try {
      await deleteRecord(id)
      fetchRecords()
    } catch (error) {
      console.error('Error deleting record:', error)
    }
  }

  return (
    <div className="App">
      <h1>Album Collector</h1>
      
      <div className="form-container">
        <h2>Add New Record</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <input
              name="artist"
              value={newRecord.artist}
              onChange={handleInputChange}
              placeholder="Artist"
              required
            />
            <input
              name="title"
              value={newRecord.title}
              onChange={handleInputChange}
              placeholder="Title"
              required
            />
          </div>
          <div className="form-group">
            <input
              name="genre"
              value={newRecord.genre}
              onChange={handleInputChange}
              placeholder="Genre"
            />
            <input
              name="medium"
              value={newRecord.medium}
              onChange={handleInputChange}
              placeholder="Medium"
            />
            <input
              name="year"
              type="number"
              value={newRecord.year}
              onChange={handleInputChange}
              placeholder="Year"
            />
          </div>
          <div className="form-group checkbox-group">
            <label>
              Favourite
              <input
                name="favourite"
                type="checkbox"
                checked={newRecord.favourite}
                onChange={handleInputChange}
              />
            </label>
            <button type="submit">Add Record</button>
          </div>
        </form>
      </div>

      <div className="records-container">
        <h2>Collection</h2>
        <table>
          <thead>
            <tr>
              <th>Artist</th>
              <th>Title</th>
              <th>Genre</th>
              <th>Medium</th>
              <th>Year</th>
              <th>Favourite</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {records.map(record => (
              <tr key={record.id}>
                <td>{record.artist}</td>
                <td>{record.title}</td>
                <td>{record.genre}</td>
                <td>{record.medium}</td>
                <td>{record.year}</td>
                <td>{record.favourite ? '★' : ''}</td>
                <td>
                  <button className="delete" onClick={() => handleDelete(record.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default App

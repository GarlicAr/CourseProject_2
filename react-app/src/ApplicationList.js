import React, {useState, useEffect} from 'react';
import axios from 'axios';
import './styles.css';

const ApplicationList = () => {
    const [applications, setApplications] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/applications/all');
                setApplications(response.data);
            }
            catch(error) {
                console.error("Fetching data error: " + error);
            }
        };
        fetchData();
    }, []);

    const onDelete = async (appId) => {
        try {

            await axios.post('http://localhost:8080/applications/delete/' + appId)

        }
        catch (error) {
            console.error(error);
        }


    }


    return (
        <div>
            <h1>All applications</h1>
            <table>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Text</th>
                        <th>Author ID</th>
                        <th>Recipient Person ID</th>
                    </tr>
                </thead>
                <tbody>
                    {applications.map((app) => (
                        <tr key={app.applicationId}>
                            <td>{app.title}</td>
                            <td>{app.text}</td>
                            <td>{app.author.personId}</td>
                            <td>
                                {app.recipients.length > 0
                                    ? app.recipients.map((recipient) => recipient.personId).join(', ')
                                    : 'N/A'}
                            </td>
                            <td onClick={() => onDelete(app.applicationId)}>DELETE</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};



export default ApplicationList;
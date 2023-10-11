import React, {useState, useEffect} from 'react';
import axios from 'axios';
import './styles.css';

const ApplicationList = () => {
    const [applications, setApplications] = useState([]);

    const fetchData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/applications/all');
            setApplications(response.data.map(app => ({...app, isUpdating: false, updatedTitle: app.title, updatedText: app.text})));
        } catch (error) {
            console.error("Fetching data error: " + error);
        }
    };

    useEffect(() => {
        fetchData(); 
    }, []);


    const onDelete = async (appId) => {
        try {

            await axios.delete(`http://localhost:8080/applications/delete/${appId}`)

            fetchData();

        }
        catch (error) {
            console.error(error);
        }


    }

    const onUpdateClick = (appId) => {

        setApplications(prevApps => prevApps.map(app =>
            app.applicationId === appId
                ? { ...app, isUpdating: true }
                : { ...app, isUpdating: false }
        ));

    }

    const onUpdateSubmit = async (appId, updatedTitle, updatedText) => {
        try {

            await axios.put(`http://localhost:8080/applications/update/${appId}`, {
                title: updatedTitle,
                text: updatedText
            });

            fetchData();
            console.log(`Application with id: ${appId} has been updated!`);

        }catch (e) {
            console.error(e);
        }

    }

    const toggleUpdating = (appId) => {

        setApplications(prevApps => prevApps.map(prevApp =>
            prevApp.applicationId === appId
                ? { ...prevApp, isUpdating: false }
                : prevApp
        ));
        
    };




    return (
        <div>
            <h1>All applications</h1>
            <table>
                <th>ID</th>
                <th>Title</th>
                <th>Text</th>
                <th>Author ID</th>
                <th></th>
                <th></th>
                <th></th>
                <tbody>
                    {applications.map((app) => (
                        <tr key={app}>
                            <td>
                                {app.applicationId}
                            </td>

                            <td>{!app.isUpdating ? (app.title) : (<input type="text" value={app.updatedTitle} onChange={(e) => setApplications(prevApps => prevApps.map(prevApp =>
                                prevApp.applicationId === app.applicationId
                                    ? { ...prevApp, updatedTitle: e.target.value }
                                    : prevApp
                            ))} />)}</td>

                            <td>{!app.isUpdating ? (app.text) : (<input type="text" value={app.updatedText} onChange={(e) => setApplications(prevApps => prevApps.map(prevApp =>
                                prevApp.applicationId === app.applicationId
                                    ? { ...prevApp, updatedText: e.target.value }
                                    : prevApp
                            ))} />)}</td>

                            <td>{app.author.personId}</td>

                            <td><button onClick={() => onDelete(app.applicationId)}>DELETE</button></td>

                            <td>{!app.isUpdating ? (<button onClick={() => onUpdateClick(app.applicationId)}>UPDATE</button>) :

                                (<button onClick={() => onUpdateSubmit(app.applicationId, app.updatedTitle, app.updatedText)}>SUBMIT</button>)}</td>

                            <td>
                                {app.isUpdating && (
                                    <button onClick={() => toggleUpdating(app.applicationId)}>Cancel</button>
                                )}
                            </td>
                            

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};


export default ApplicationList;
import { useEffect, useState } from "react";
import { getOne } from "../../api/todoApi";

const initState = {
    tno: 0,
    title: '',
    writer: '',
    dueDate: null,
    complete: false
}

const ModifyComponent = ({tno, moveList, moveRead}) => {
    const [todo, setTodo] = useState({...initState})

    useEffect(() => {
        getOne(tno).then(data => setTodo(data))
    }, [tno])

    const handleChangeTodo = (e) => {
        const { name, value } = e.target;
        setTodo(prevTodo => ({ ...prevTodo, [name]: value }));  // Computed Property Names
    }

    const handleChangeTodoComplete = (e) => {
        const { value } = e.target;
        setTodo(prevTodo => ({ ...prevTodo, complete: value === 'Y' }));
    }

    return (
        <div className="border-2 border-sky-200 mt-10 m-2 p-4">

            {/* Modify input 추가 */}

            <div className="flex justify-end p-4">
                <button type="button" className="rounded p-4 m-2 text-xl w-32 text-white bg-red-500">Delete</button>
                <button type="button" className="rounded p-4 m-2 text-xl w-32 text-white bg-blue-500">Modify</button>
            </div>
        </div>
    );
}

export default ModifyComponent;
import { useState } from "react";
import { postAdd } from "../../api/todoApi";

const initState = {
    title: '',
    writer: '',
    dueDate: ''
}

const AddComponent = () => {
    const [todo, setTodo] = useState({...initState})    // Spread 연산자, 불변성 유지 

    const handleChangeTodo = (e) => {
        // todo[e.target.name] = e.target.value            // 객체 리터럴 (신규 기능)
        // setTodo({...todo})
        const { name, value } = e.target;
        setTodo(prevTodo => ({ ...prevTodo, [name]: value }));  // Computed Property Names
    }

    const handleClickAdd = () => {
        console.log(todo)
        
        postAdd(todo).then(result => {
            console.log(result)
            setTodo({...initState})
        }).catch(e => {
            console.log(e)
        })
    }

    return (
        <div className="border-2 border-sky-200 mt-10 p-4">
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">TITLE</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" name="title" type={'text'} value={todo.title} onChange={handleChangeTodo}></input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">WRITER</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" name="writer" type={'text'} value={todo.writer} onChange={handleChangeTodo}></input>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="relative mb-4 flex w-full flex-wrap items-stretch">
                    <div className="w-1/5 p-6 text-right font-bold">DUE DATE</div>
                    <input className="w-4/5 p-6 rounded-r border border-solid border-neutral-500 shadow-md" name="dueDate" type={'date'} value={todo.dueDate} onChange={handleChangeTodo}></input>
                </div>
            </div>
            <div className="flex justify-end">
                <div className="relative mb-4 flex p-4 flex-wrap items-stretch">
                    <button type="button" className="rounded p-4 w-36 bg-blue-500 text-xl text-white" onClick={handleClickAdd}>ADD</button>
                </div>
            </div>
        </div>
    );
}

export default AddComponent;
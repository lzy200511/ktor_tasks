# Task 3 usage

## Task 3.1: local server

From the repository root:

```sh
cd task3_1
python -m http.server
```

Use `python3` if that is your Python command. Open
http://localhost:8000/test.html (the task lists http://0.0.0.0:8000/test.html).
The terminal should log the GET request. Stop the server with Ctrl+C.

## Task 3.2: browser forms

Open `../task3_2/form-get.html` for the original GET form or
`../task3_2/form.html` for the final POST form required by the guide.
`form-post.html` is also retained as a POST example. Both submit the same
`email` field to the required HTTPBin endpoint.

GET places the URL-encoded field in the query string. POST sends it in the
request body using `application/x-www-form-urlencoded`. Browser developer
tools' Network panel can show the method, URL, headers, and form data.

## Task 3.3: uv and HTTPX

Install uv using https://docs.astral.sh/uv/getting-started/installation/.
From `task3_3`, run:

```sh
uv sync
uv run httpx --help
uv run httpx https://httpbin.io/dump/request -m GET -p email student@example.com
uv run httpx https://httpbin.io/dump/request -m POST -d email student@example.com
```

The committed project configuration supplies the project and dependency
declarations normally created by `uv init --vcs none` and
`uv add 'httpx[cli]'`. Do not reinitialize the existing project.
`uv sync` creates the local environment and generates `uv.lock`.

An additional Python example prints the outgoing request and incoming response:

```sh
uv run main.py GET --email student@example.com
uv run main.py POST --email student@example.com
```

These commands make real requests only when run. No browser checks, request
experiments, or dependency installation were performed for this submission.

## Task 3.2: request comparison

For an example address of student@example.com, GET submits
`/dump/request?email=student%40example.com`. The `%40` encodes the
`@` character. This form's GET request has no body.

The final POST form sends to `/dump/request` with the encoded field
`email=student%40example.com` in its body instead of the query string.

Refreshing the GET result normally repeats the request immediately.
A browser may send a Cache-Control header to control cache reuse.
Refreshing a POST result may prompt for confirmation before resubmitting,
because repeating a POST can repeat changes on the server. Exact browser
behaviour and headers vary. POST alone does not prevent duplicate submissions.

## Task 3.3: virtual environment and response headers

For a new, empty project, the guide's setup commands are:

```sh
uv init --vcs none
uv add 'httpx[cli]'
```

For this existing project, use `uv sync` as described above. Activate
the resulting environment:

Linux/macOS:
```sh
source .venv/bin/activate
```

Windows Command Prompt:
```bat
.venv\Scripts\activate
```

Windows PowerShell:
```powershell
.\.venv\Scripts\Activate.ps1
```

Then run the guide's request:
```sh
httpx https://www.example.com
```

Alternatively, without activating:
```sh
uv run httpx https://www.example.com
```

The CLI displays the response status, headers, and body. A status line such
as `HTTP/1.1 200 OK` identifies the HTTP version, status code, and reason
phrase; 200 means the request succeeded.

| Header | Meaning |
| --- | --- |
| Content-Type | Identifies the media type; text/html indicates HTML. |
| Content-Encoding | gzip means the representation was compressed with gzip; HTTPX normally decodes it for display. |
| Content-Length | Size in bytes of the transmitted representation, including compression when used; not necessarily the length of displayed text. |
| Connection | keep-alive indicates a persistent connection, allowing reuse for subsequent requests; HTTP/1.1 connections are persistent by default unless closed. |

The guide's example mentions eleven header lines and a compressed length
of 363 bytes. These are example values, not requirements for current responses.
Headers, compression, status, and content length may change over time.
A blank line separates the headers from the HTML body.

When finished with an activated environment, run:
```sh
deactivate
```

These explanations are based on the supplied course screenshots, not on a
recorded execution. Browser and HTTP request experiments were skipped as
requested.

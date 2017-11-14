require './song'
require 'sinatra'
require 'sass'
require 'slim'

get('/styles.css'){ scss :styles }

configure do
  enable :sessions
  set :username, 'frank'
  set :password, 'sinatra'
end

get '/set/:name' do
  session[:name] = params[:name]
end

get '/get/hello' do
  "Hello #{session[:name]}"
end

get '/' do
  slim :home
end

get '/about' do
  @title = "All About This Website"
  slim :about
end

get '/contact' do
  slim :contact
end

not_found do
  slim :not_found
end

get '/songs' do
  @songs = Song.all
  slim :songs
end

get '/songs/new' do
  redirect to('/login') unless session[:admin]
  @song = Song.new
  slim :new_song
end

get '/songs/:id' do
  @song = Song.get(params[:id])
  slim :show_song
end

delete '/songs/:id' do
  redirect to('/login') unless session[:admin]
  Song.get(params[:id]).destroy
  redirect to('/songs')
end

post '/songs' do
  redirect to('/login') unless session[:admin]
  song = Song.create(params[:song])
  redirect to("/songs/#{song.id}")
end

get '/songs/:id/edit' do
  redirect to('/login') unless session[:admin]
  @song = Song.get(params[:id])
  slim :edit_song
end

put '/songs/:id' do
  redirect to('/login') unless session[:admin]
  song = Song.get(params[:id])
  song.update(params[:song])
  redirect to("/songs/#{song.id}")
end

get '/login' do
  slim :login
end

post '/login' do
  if params[:username] == settings.username && params[:password] == settings.password
    session[:admin] = true
    redirect to('/songs')
  else
    slim :login
  end
end

get '/logout' do
  session.clear
  redirect to('/login')
end